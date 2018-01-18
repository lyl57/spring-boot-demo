package com.example.search.domain;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Created by lyl57 on 2018/1/18
 */
@Document(indexName = "test", type = "user", shards = 10, replicas = 0, refreshInterval = "-1")
public class UserEntity {
    private long id;
    private String name;
    private String email;

    public UserEntity(long id, String name, String email) {
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
