package com.scofield.app.domains.person;

import java.util.HashSet;
import com.scofield.app.domains.interfaces.IRepository;

public class PersonRepository implements IRepository<Person>{
    private HashSet<Person> personHasSet = new HashSet<Person>();

    public HashSet<Person> getAll(){
        return this.personHasSet;
    }

    public Person getById(long id){
        return personHasSet.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);                   
    }

    public Person register(Object o){
        
        personHasSet.add((Person) o);
        return Person;
    }
}