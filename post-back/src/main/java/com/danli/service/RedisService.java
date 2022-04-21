package com.danli.service;

import java.util.Map;

/**
 * redis
 *
 * @author jmy
 * @date  2022/4/5
 */
public interface RedisService {








	/**
	 * key set delete an object
	 *
	 * @param key
	 * @param value
	 * @return void
	 */
	void saveValueToSet(String key, Object value);


	/**
	 *
	 *
	 * @param key
	 * @param value
	 * @return void
	 */
	void deleteValueBySet(String key, Object value);

	/**
	 *
	 * @param key
	 * @param value
	 * @return void
	 */
	boolean hasValueInSet(String key, Object value);

	/**
	 * delete（set、String、hash、list、sortedList）
	 *
	 * @param key
	 * @return void
	 */
	void deleteCacheByKey(String key);

	/**
	 * Query whether a key exists in redis
	 *
	 * @param key
	 * @return boolean
	 */
	boolean hasKey(String key);


	boolean hasHashKey(String key, Object value);

	void saveKVToHash(String hash, Object key, Object value);

	void saveMapToHash(String hash, Map map);

	Map getMapByHash(String hash);

	Object getValueByHashKey(String hash, Object key);

	void incrementByHashKey(String hash, Object key, int increment);


	void deleteByHashKey(String hash, Object key);

	int countBySet(String key);

	void deleteAllKeys();

	void incrementByKey(String key, int increment);

	void saveObjectToValue(String key, Object object);
	Object getObjectByValue(String key);
    void expire(Object redisKey, int seconds);
}
