package com.taotao.rest.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * jedis测试类
 * Created by mutoulazy on 2017/10/19.
 */
public class JedisTest {

    @Test
    public void testRedisSingle(){
        //创建一个jedis对象
        Jedis jedis = new Jedis("192.168.187.153",6379);
        //调用jedis对象的方法，方法名称与redis的一样
        jedis.set("key1","jedis test");
        String result = jedis.get("key1");
        System.out.println("result: "+result);
        //关闭jedis
        jedis.close();
    }

    /**
     * 使用jedis的连接池
     */
    @Test
    public void testJedisPool(){
        JedisPool pool = new JedisPool("192.168.187.153",6379);

        Jedis jedis = pool.getResource();
        String result = jedis.get("key1");
        System.out.println("result: "+result);

        jedis.close();
        pool.close();
    }

    /**
     * 测试Jedis连接Redis的集群
     */
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.187.153",7001));
        nodes.add(new HostAndPort("192.168.187.153",7002));
        nodes.add(new HostAndPort("192.168.187.153",7003));
        nodes.add(new HostAndPort("192.168.187.153",7004));
        nodes.add(new HostAndPort("192.168.187.153",7005));
        nodes.add(new HostAndPort("192.168.187.153",7006));

        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("key1","1000");
        String result = cluster.get("key1");
        System.out.println("result: " + result);

        cluster.close();
    }

    /**
     * Spring整合jedis单机版测试
     */
    @Test
    public void testSpringJedisSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String result = jedis.get("key1");
        System.out.println("result: " + result);
        jedis.close();
        pool.close();
    }

    /**
     * Spring整合jedis集群版测试
     */
    @Test
    public void testSpringJedisCluster(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
        String result = cluster.get("key1");
        System.out.println("result: " + result);
        cluster.close();
    }
}
