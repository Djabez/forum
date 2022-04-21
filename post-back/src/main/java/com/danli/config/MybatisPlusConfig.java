package com.danli.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * MybatisPlus configuration
 *
 * @author Yicong Wang
 * @date  2022
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.danli.mapper")
public class MybatisPlusConfig {
    /**
     * Paging plug-in
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}

