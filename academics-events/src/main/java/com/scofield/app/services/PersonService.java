package com.scofield.app.services;


import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.domains.person.Professor;

import java.util.ArrayList;
import java.util.HashSet;
import com.scofield.app.domains.event.Event;
import com.scofield.app.domains.person.ExternalParticipant;
import com.scofield.app.domains.person.Person;
import com.scofield.app.domains.person.Student;

public class PersonService extends Service<Person>{
    public PersonService(PersonRepository repository ){
        super(repository);
    }

    public Person register(String name, String cpf, Object objectParams){
        try{
            Person newPerson;
            if((objectParams instanceof String)){
                newPerson = new Student(name,  cpf, (String)objectParams);
            }
            else if((objectParams instanceof ArrayList)){
                 ArrayList<?> rawList = (ArrayList<?>) objectParams;

                for (Object item : rawList) {
                    if (!(item instanceof String)) {
                        throw new IllegalArgumentException("All elements in subjects list must be strings");
                    }
                }
    
                ArrayList<String> subjectList = (ArrayList<String>) rawList;
                newPerson = new Professor(name,  cpf, subjectList);
            }
            else if((objectParams instanceof Integer)){
                newPerson = new ExternalParticipant(name,  cpf, (Integer)objectParams);
            }
            else{
                throw new IllegalArgumentException("Invalid params to register an person");
            }       
             
            repository.register(newPerson);
            return newPerson;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register an person: " + ex.getMessage());
        }     
    }

    public HashSet<Event> getAllEventsPerson(long personId){
        Person person = (Person)repository.getById(personId);
        if(person == null){
            throw new IllegalArgumentException("Person doesn't exist");
        }
        return person.getEvents();
    
    }
    
    public Event addEventPerson(Person person, Event event){
        person.addEvent(event);
        return event;
    }
}