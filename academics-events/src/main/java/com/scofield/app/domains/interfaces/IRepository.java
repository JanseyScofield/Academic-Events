package com.scofield.app.domains.interfaces;

import java.util.HashSet;

public interface IRepository<T> {
    public HashSet<T> getAll();
    public T getById(long id);
    public T register(Object o);
}