package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
//		redisTemplate.opsForValue().set("test","val");
		redisTemplate.delete("test");
	}

	@Test
	public void testList(){
		List<String>list=new ArrayList<>();
		for (int i=0;i<10;i++) {
			list.add("test>>>>>>>>>"+i);
		}
		String testKey="testlist";
		redisTemplate.opsForList().rightPush(testKey,list);
//		System.out.println(redisTemplate.);

		List<String> testlist = (List<String>) redisTemplate.opsForList().range(testKey,0,-1);

		//对testKey新加值
		System.out.println(testlist.size());
		redisTemplate.opsForList().rightPush(testKey,"ceshixinjia");

		System.out.println(testlist.size());

		List<String> testlist2 = (List<String>) redisTemplate.opsForList().range(testKey,0,-1);

	}

	@Test
	public void testSet(){
		String key="testSet";
//		redisTemplate.opsForSet().add(key,"3");
//		redisTemplate.opsForSet().add(key,"4");
//		redisTemplate.opsForSet().add(key,"1");

		Set set = redisTemplate.opsForSet().members(key);

//		redisTemplate.opsForSet().add(key,"6");

		set=redisTemplate.opsForSet().members(key);

		System.out.println(set.size());

	}

	@Test
	public void testZset(){
		String key="testZset";
		redisTemplate.opsForZSet().add(key,"2",3);
		redisTemplate.opsForZSet().add(key,"1",1);
		redisTemplate.opsForZSet().add(key,"2",2);
		redisTemplate.opsForZSet().add(key,"1",2);
		redisTemplate.opsForZSet().add(key,"3",2);
		Set range = redisTemplate.opsForZSet().range(key, 0, -1);
		System.out.println(range.size());

	}

	@Test
	public void testBound(){
		String key="testZset";
		BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(key);
		Set<String> range = boundZSetOperations.range(0, -1);
		range.forEach(System.out::println);
	}

	@Test
	public void test(){
		String key="testZset";
		BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(key);
		Set<String> range = boundZSetOperations.range(0, -1);
		range.forEach(System.out::println);
		System.out.println(11111);
	}
}
