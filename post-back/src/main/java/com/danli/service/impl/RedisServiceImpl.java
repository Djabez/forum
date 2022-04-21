package com.danli.service.impl;
/**
 * redis
 *
 * @author Mingyu.jin
 * @date  2022/4/8
 */

import com.danli.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisService {

	@Qualifier("redisTemplateJackson")
	@Autowired()
	RedisTemplate jsonRedisTemplate;


	@Override
	public void saveValueToSet(String key, Object value) {
		jsonRedisTemplate.opsForSet().add(key, value);
	}


	@Override
	public void deleteValueBySet(String key, Object value) {
		jsonRedisTemplate.opsForSet().remove(key, value);
	}


	@Override
	public boolean hasValueInSet(String key, Object value) {
		return jsonRedisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public void deleteCacheByKey(String key) {
		jsonRedisTemplate.delete(key);
	}


	@Override
	public boolean hasKey(String key) {
		return jsonRedisTemplate.hasKey(key);
	}






	@Override
	public void saveKVToHash(String hash, Object key, Object value) {
		jsonRedisTemplate.opsForHash().put(hash, key, value);
	}

	@Override
	public void saveMapToHash(String hash, Map map) {
		jsonRedisTemplate.opsForHash().putAll(hash, map);
	}

	@Override
	public Map getMapByHash(String hash) {
		return jsonRedisTemplate.opsForHash().entries(hash);
	}

	@Override
	public Object getValueByHashKey(String hash, Object key) {
		return jsonRedisTemplate.opsForHash().get(hash, key);
	}

	@Override
	public boolean hasHashKey(String hash, Object key) {
		return jsonRedisTemplate.opsForHash().hasKey(hash,key);
	}

	@Override
	public void incrementByHashKey(String hash, Object key, int increment) {
		if (increment < 0) {
			throw new RuntimeException("The increment factor must be greater than 0");
		}
		jsonRedisTemplate.opsForHash().increment(hash, key, increment);
	}








	@Override
	public void deleteByHashKey(String hash, Object key) {
		jsonRedisTemplate.opsForHash().delete(hash, key);
	}

	@Override
	public int countBySet(String key) {
		return jsonRedisTemplate.opsForSet().size(key).intValue();

	}



	@Override
	public void deleteAllKeys() {
		jsonRedisTemplate.delete(jsonRedisTemplate.keys("*"));
	}

	@Override
	public void expire(Object key, int secondes) {
		jsonRedisTemplate.expire(key,secondes, TimeUnit.SECONDS);
	}

	@Override
	public void incrementByKey(String key, int increment) {
		if (increment < 0) {
			throw new RuntimeException("The increment factor must be greater than 0");
		}
		jsonRedisTemplate.opsForValue().increment(key, increment);
	}

	@Override
	public void saveObjectToValue(String key, Object object) {
		jsonRedisTemplate.opsForValue().set(key, object);
	}

	@Override
	public  Object getObjectByValue(String key) {
		Object redisResult = jsonRedisTemplate.opsForValue().get(key);
		return redisResult;
	}


}
