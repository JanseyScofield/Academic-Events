package com.scofield.app.services;

import java.util.HashSet;
import com.scofield.app.domains.abstracts.Repository;
import com.scofield.app.domains.interfaces.IIdentifiedEntity;

public abstract class Service<T  extends IIdentifiedEntity> {
    protected Repository repository;

    public Service(Repository repository){
        this.repository = repository;
    }

    public HashSet<T> getAll() {
        return new HashSet<>(repository.getAll());
    }

    public IIdentifiedEntity getById(long id){
        if(id < 1){
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        IIdentifiedEntity response = repository.getById(id);
        if(response == null){
            throw new IllegalArgumentException("Not found");
        }

        return response;
    }
}
