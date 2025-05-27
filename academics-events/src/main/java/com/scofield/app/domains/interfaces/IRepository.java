package com.scofield.app.domains.interfaces;

import java.util.HashSet;

public interface IRepository {
    public HashSet<T> GetAll();
    public T GetById(long id);
    public boolean Register(Object o);
}