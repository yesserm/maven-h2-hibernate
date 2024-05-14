package com.yesser.app.services;

import com.yesser.app.dao.IGenericDAO;

import java.util.List;

public interface IGenericService<T> extends IGenericDAO<T> {
    List<T> getAll();
    void deleteAll();
    T getById(int i);
    T getId(Long i);
    T getByName(String name);
}
