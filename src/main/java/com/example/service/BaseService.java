package com.example.service;

import com.example.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lyl57 on 2017/3/22.
 */
public class BaseService<D extends CrudRepository<T, Long>, T extends BaseEntity> {
    @Autowired
    D dao;

    public T get(Long id) {
        return dao.findOne(id);
    }

    public T save(T t) {
        t = dao.save(t);
        return t;
    }

    public Iterable<T> save(Iterable<T> list) {
        return dao.save(list);
    }

    public void delete(Long id) {
        dao.delete(id);
    }


}
