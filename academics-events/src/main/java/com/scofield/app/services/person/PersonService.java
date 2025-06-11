// package com.scofield.app.services.person;

// import com.scofield.app.domains.person.Person;
// import java.util.HashSet;
// import com.scofield.app.domains.person.PersonRepository;

// public class PersonService extends Service<Person> {
//     public PersonService(PersonRepository personRepository){
//         super(personRepository);  
//     }

//     @Override
//     public Person register(String name, String cpf){
//         try{
//             Person newPerson = new Person(name, cpf);
//             personRepository.register(newPerson);
//             return newPerson;
//         }
//         catch (IllegalArgumentException ex){
//             throw new IllegalArgumentException("Error to register an person: " + ex.getMessage());
//         }
//         catch (Exception ex){
//             throw new RuntimeException("Internal server error: " + ex.getMessage());
//         }
//     }
// }
