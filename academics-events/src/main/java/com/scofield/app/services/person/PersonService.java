package com.scofield.app.services.person;


import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.domains.person.Professor;

import java.util.ArrayList;
import com.scofield.app.domains.person.ExternalParticipant;
import com.scofield.app.domains.person.Person;
import com.scofield.app.domains.person.Student;
import com.scofield.app.services.abstratcs.Service;

public class PersonService extends Service<Person>{
    public PersonService(PersonRepository repository ){
        super(repository);
    }

    @Override
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
                throw new IllegalArgumentException("Invalid params to register a person");
            }       
             
            repository.register(newPerson);
            return newPerson;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register an student: " + ex.getMessage());
        }
        
    }
}