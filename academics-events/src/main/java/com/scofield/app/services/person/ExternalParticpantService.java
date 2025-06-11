// package com.scofield.app.services.person;


// import com.scofield.app.domains.person.PersonRepository;
// import com.scofield.app.domains.person.Student;
// import com.scofield.app.domains.person.ExternalParticipant;
// import com.scofield.app.services.abstratcs.Service;

// public class ExternalParticpantService extends Service<ExternalParticipant>{
//     public ExternalParticpantService(PersonRepository repository ){
//         super(repository);
//     }

//     @Override
//     public ExternalParticipant register(String name, String cpf, Object authorizantionToken){
//         try{
//             if(!(authorizantionToken instanceof String)){
//                 throw new IllegalArgumentException("To register an external participant, authorizantion token must be an string");
//             }
//             ExternalParticipant newExternalParticipant = new ExternalParticipant(name,  cpf, (String)authorizantionToken);
//             repository.register(newExternalParticipant);
//             return newExternalParticipant;
//         }catch (IllegalArgumentException ex){
//             throw new IllegalArgumentException("Error to register an student: " + ex.getMessage());
//         }
        
//     }
// }