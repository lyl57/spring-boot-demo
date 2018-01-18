package com.example;

import com.example.search.domain.UserEntity;
import com.example.search.repository.ESUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    String testKey = "userService:guideTag:";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ESUserRepository ESUserRepository;

    @Test
    public void contextLoads() throws Exception {

//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i < 50000; i++) {
//            String random = RandomStringUtils.randomNumeric(4);
//            userList.add(new User(random + "name", random + "email"));
//        }
//        List<Object[]> params = new ArrayList<>();
//        for (User user: userList) {
//            Object[] o=new Object[2];
//            o[0]=user.getName();
//            o[1]=user.getEmail();
//            params.add(o);
//        }
//        int[] ints = jdbcTemplate.batchUpdate(" insert into user(namess ,email) VALUES(?,?)", params);
//        System.out.println(ints);
//        jdbcTemplate.setMaxRows(4000);
//        jdbcTemplate.setFetchSize(1);
//        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT id,now() as t FROM user");
//        System.out.println(sqlRowSet.getRow());
//        while (sqlRowSet.next()) {
//            System.out.println(sqlRowSet.getObject(2));
//            Thread.sleep(1000L);
//            System.out.println(sqlRowSet.getRow());
//        }
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id,now() as t FROM user",
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ps.setFetchSize(100);
        ps.setFetchDirection(ResultSet.FETCH_REVERSE);
        ResultSet rs = ps.executeQuery();
        System.out.println(rs.getRow());
        while (rs.next()) {
            System.out.println(rs.getObject(2));
            Thread.sleep(10L);
            System.out.println(rs.getRow());
        }
    }

    @Test
    public void testList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("test>>>>>>>>>" + i);
        }
        String testKey = "testlist";
        redisTemplate.opsForList().rightPush(testKey, list);
//		System.out.println(redisTemplate.);

        List<String> testlist = (List<String>) redisTemplate.opsForList().range(testKey, 0, -1);

        //对testKey新加值
        System.out.println(testlist.size());
        redisTemplate.opsForList().rightPush(testKey, "ceshixinjia");

        System.out.println(testlist.size());

        List<String> testlist2 = (List<String>) redisTemplate.opsForList().range(testKey, 0, -1);

    }

    @Test
    public void testSet() {
        String key = "testSet";
//		redisTemplate.opsForSet().add(key,"3");
//		redisTemplate.opsForSet().add(key,"4");
//		redisTemplate.opsForSet().add(key,"1");

//        Set set = redisTemplate.opsForSet().members(key);

//		redisTemplate.opsForSet().add(key,"6");

//        set = redisTemplate.opsForSet().members(key);

        //set1
//        redisTemplate.opsForSet().add(testKey + "1", 1);
//        redisTemplate.opsForSet().add(testKey + "1", 2);

        //set2  男：3、4、5、7、8、9、10、11  女： 3、4、5、6、11、14、15、16
        String[] man = "3、4、5、7、8、9、10、11".split("、");
        String[] women = "3、4、5、6、11、14、15、16".split("、");
        for (int i = 0; i < man.length; i++) {
            redisTemplate.opsForSet().add(testKey + "2:" + "1", Integer.parseInt(man[i]));
            redisTemplate.opsForSet().add(testKey + "2:" + "2", Integer.parseInt(women[i]));
        }
//        System.out.println(set.size());

    }

    @Test
    public void testZset() {
        String key = "testZset";
        redisTemplate.opsForZSet().add(key, "2", 3);
        redisTemplate.opsForZSet().add(key, "1", 1);
        redisTemplate.opsForZSet().add(key, "2", 2);
        redisTemplate.opsForZSet().add(key, "1", 2);
        redisTemplate.opsForZSet().add(key, "3", 2);
        Set range = redisTemplate.opsForZSet().range(key, 0, -1);
        System.out.println(range.size());

    }

    @Test
    public void testBound() {
        String key = "testZset";
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(key);
        Set<String> range = boundZSetOperations.range(0, -1);
        range.forEach(System.out::println);
    }

    @Test
    public void test() {
        String key = "testZset";
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(key);
        Set<String> range = boundZSetOperations.range(0, -1);
        range.forEach(System.out::println);
        System.out.println(11111);
    }

    @Test
    public void testEs() {
        UserEntity entity = new UserEntity(1,"测试1","888@qq.com");
        ESUserRepository.save(entity);
    }



}
