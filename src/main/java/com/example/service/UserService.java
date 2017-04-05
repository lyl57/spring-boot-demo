package com.example.service;

import com.alibaba.fastjson.JSON;
import com.example.domain.User;
import com.example.domain.UserBook;
import com.example.domain.UserInfo;
import com.example.repository.UserRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl57 on 2017/3/22.
 */
@Service
@Transactional
public class UserService extends BaseService<UserRepository,User>{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    RedisTemplate redisTemplate;

    String USER_HASH_KEYS=User.class.getName();


    @Override
    public User save(User user) {
        user=super.save(user);
        redisTemplate.opsForHash().put(USER_HASH_KEYS,user.getId()+"",JSON.toJSONString(user));
        return user;
    }

    @Override
    public User get(Long id) {
        String s = (String) redisTemplate.opsForHash().get(USER_HASH_KEYS, id.toString());
        User user = JSON.parseObject(s,User.class);
        if(user!=null){
            return user;
        }
        user=super.get(id);
        redisTemplate.opsForHash().put(user.getClass().getName(),user.getId()+"",JSON.toJSONString(user));
        return user;
    }

    public List<UserInfo> findUser(User user) {
        List<UserInfo> list;
        Session session = sessionFactory.getCurrentSession();
            StringBuffer sb=new StringBuffer("SELECT u.id,u.name,u.email FROM user u");
        Query query = session.createSQLQuery(sb.toString())
                .addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("email", StringType.INSTANCE)
        .setResultTransformer(new AliasToBeanResultTransformer(UserInfo.class));
         list = query.list();
        return list;
    }
}
