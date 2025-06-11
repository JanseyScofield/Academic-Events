package com.scofield.app.domains.abstracts;

import java.util.HashSet;
import com.scofield.app.domains.interfaces.IIdentifiedEntity;

public abstract class Repository<T extends IIdentifiedEntity> {
    protected HashSet<T> hashSet = new HashSet<T>();

    public HashSet<T> getAll(){
        return this.hashSet;
    }

    public T getById(long id){
        return hashSet.stream()
                            .filter(i -> i.getId() == id)
                            .findFirst()
                            .orElse(null);
    }

    public T register(T o){ 
        hashSet.add(o);
        return o;
    }
}