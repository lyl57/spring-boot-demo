package com.example.domain;

import javax.persistence.*;

/**
 * Created by lyl57 on 2017/3/22.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
