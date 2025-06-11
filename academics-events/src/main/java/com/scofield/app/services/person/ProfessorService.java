package com.scofield.app.services.person;


import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.domains.person.Professor;
import com.scofield.app.services.abstratcs.Service;
import java.util.ArrayList;

public class ProfessorService extends Service<Professor>{
    public ProfessorService(PersonRepository repository ){
        super(repository);
    }

    @Override
    public Professor register(String name, String cpf, Object listSubjects){
        try{
            if(!(listSubjects instanceof ArrayList)){
                throw new IllegalArgumentException("To register an professor, subjects must be an ArrayList");
            }

            ArrayList<?> rawList = (ArrayList<?>) listSubjects;

            for (Object item : rawList) {
                if (!(item instanceof String)) {
                    throw new IllegalArgumentException("All elements in subjects list must be strings");
                }
            }
  
            ArrayList<String> subjectList = (ArrayList<String>) rawList;
            Professor newProfessor = new Professor(name,  cpf, subjectList);
            repository.register(newProfessor);
            return newProfessor;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register an student: " + ex.getMessage());
        }
        
    }
}