package com.danli.Schedule;

import com.danli.config.RedisKeyConfig;
import com.danli.entity.Blog;
import com.danli.service.BlogService;
import com.danli.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * schedule
 * @author: Mingyu.jin
 * @date: 2022/3/11
 */
@Component
@EnableScheduling
@EnableAsync
public class RedisSyncScheduleTask {

    @Autowired
    RedisService redisService;
    @Autowired
    BlogService blogService;

    Logger logger = LoggerFactory.getLogger(RedisSyncScheduleTask.class);


    @Async
    @Scheduled(fixedDelay = 24*60*60*1000)
    public void syncBlogViewsToDatabase() {
        logger.info("hhh");
        String redisKey = RedisKeyConfig.BLOG_VIEWS_MAP;
        Map blogViewsMap = redisService.getMapByHash(redisKey);
        Set<Integer> keys = blogViewsMap.keySet();
        for (Integer key : keys) {
            Integer views = (Integer) blogViewsMap.get(key);
            Blog blog = blogService.getById(key);
            blog.setViews(blog.getViews()+views);
            blogService.saveOrUpdate(blog);
        }
        deleteAllCache();
        logger.info("complete");
    }

    /**
     * clean all the cache
     */

    public void deleteAllCache() {
        redisService.deleteAllKeys();
    }


}
