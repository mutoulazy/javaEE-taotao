package com.taotao.rest.dao;

/**
 * 应对开发与生产的不同需求，本接口适配集群版和单机版的jedis使用。
 * Created by mutoulazy on 2017/10/20.
 */
public interface JedisClient {
    //String类型的键值对获取与设置方法
    String get(String key);
    String set(String key, String value);
    //Hash类型的键值对获取与设置方法
    String hget(String hkey, String key);
    long  hset(String hkey, String key, String value);
    //键自增1
    long incr(String key);
    //设置数据过期时间
    long expire(String key, int second);
    //查询数据是否过期（非零数表示剩余时间，-1 表示永久存在，-2 表示数据已经过期）
    long ttl(String key);
    //删除String类型key
    long del(String key);
    //删除Hash类型的key
    long hDel(String hkey, String key);
}
