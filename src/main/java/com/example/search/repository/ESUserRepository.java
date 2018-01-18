package com.example.search.repository;

import com.example.search.domain.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Created by lyl57 on 2018/1/18
 */
public interface ESUserRepository extends ElasticsearchRepository<UserEntity, Long> {
}
