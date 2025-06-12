package com.scofield.app.ui;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.scofield.app.domains.person.Person;
import com.scofield.app.domains.person.Student;

import javax.imageio.IIOException;
import com.scofield.app.domains.event.Event;
import com.scofield.app.domains.event.InPersonEvent;
import com.scofield.app.domains.event.OnlineEvent;
import com.scofield.app.domains.event.eventsTypes.Course;
import com.scofield.app.domains.event.eventsTypes.Fair;
import com.scofield.app.domains.event.eventsTypes.IEventType;
import com.scofield.app.domains.event.eventsTypes.Lecture;
import com.scofield.app.domains.event.eventsTypes.Workshop;
import com.scofield.app.domains.interfaces.IIdentifiedEntity;
import com.scofield.app.services.EventService;
import com.scofield.app.services.PersonService;

public class Ui{
    private static final Scanner scanner = new Scanner(System.in);
    
    public static int manageMainMenu(){
        System.out.println("---------------------------");
        System.out.println("Academic Events");
        System.out.println("---------------------------");
        System.out.println("Choose the module you want to access:");
        System.out.println("1 - Event");
        System.out.println("2 - Person");
        System.out.println("3 - Exit");
        return getOption(1, 3);
    }

    public static int managePersonMenu(){
        System.out.println("---------------------------");
        System.out.println("Person");
        System.out.println("---------------------------");
        System.out.println("Choose the action you want to do:");
        System.out.println("1 - List");
        System.out.println("2 - Register");
        System.out.println("3 - Generate Person Certificate");
        System.out.println("4 - Register a Person in a Event");
        System.out.println("5 - Exit");
        return getOption(1,5);
    }

    public static int manageEventMenu(){
        System.out.println("---------------------------");
        System.out.println("Events");
        System.out.println("---------------------------");
        System.out.println("Choose the action you want to do:");
        System.out.println("1 - List");
        System.out.println("2 - Generate a Event's Dashboard");
        System.out.println("3 - Register New Event");
        System.out.println("4 - Exit");
        return getOption(1, 4);
    }

    public static void printAll(HashSet<? extends IIdentifiedEntity> hashSet){
        if(hashSet.size() == 0){
            System.out.println("Don't have registred");
            return;
        }
        for(IIdentifiedEntity entity : hashSet){
            entity.printInformation();
        }
    }

    public static void registerPerson(PersonService service){
        System.out.println("What kind of person do you want to register?");
        System.out.println("1 - Student");
        System.out.println("2 - Professor");
        System.out.println("3 - External Participant");
        System.out.println("4 - Exit");
        int option = getOption(1,4);
        if(option == 4){
            return;
        }

        System.out.println("Type name:");
        String name = scanner.nextLine();
        System.out.println("Type cpf:");
        String cpf = scanner.nextLine();
        Object paramObject = new Object();
        switch (option) {
            case 1:
                System.out.println("Type registration number:");
                paramObject = scanner.nextLine();
                break;
            case 2:
                boolean exitAddSubjects = false;
                ArrayList<String> listSubjects = new ArrayList<String>();
                while(!exitAddSubjects){
                    System.out.println("Type the subject: ");
                    String newSubject = scanner.nextLine();
                    listSubjects.add(newSubject);
                    System.out.println("Add more subjects? 1 - Yes/ 2 - No");
                    exitAddSubjects = getOption(1, 2) == 2;
                }
                paramObject = listSubjects;
                break;
            case 3:
                System.out.println("Type validation token:");
                paramObject = (Integer)Integer.parseInt(scanner.nextLine());
                break;
        }

        try{
            Person newPerson = service.register(name, cpf, paramObject);
            System.out.println("New person has been registred");
            newPerson.printInformation();
        }
        catch(Throwable ex){
            System.out.println(ex.getMessage());
        }
        
    }

    public static void registerEvent(EventService service){
        System.out.println("What modality of event do you want to register?");
        System.out.println("1 - In Person");
        System.out.println("2 - Online");
        System.out.println("3 - Hybrid");
        System.out.println("4 - Exit");
        int modality = getOption(1,4);
        if(modality == 4){
            return;
        }
        try{
            System.out.println("Type title: ");
            String title = scanner.nextLine();
            System.out.print("Type the date in this format MM/dd/yyyy: ");
            String stringDate = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(stringDate, formatter);
            System.out.println("Type the capacity:");
            int capacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Type the description: ");
            String description = scanner.nextLine();
            System.out.println("Choose the event type:");
            System.out.println("1 - Course");
            System.out.println("2 - Fair");
            System.out.println("3 - Lecture");
            System.out.println("4 - Workshop");
            int eventTypeOption = getOption(1, 4);
            IEventType eventType = new Workshop();

            switch (eventTypeOption) {
                case 1:
                    eventType = new Course();
                    break;
                case 2:
                    eventType = new Fair();
                    break;
                case 3:
                    eventType = new Lecture();
                    break;
                case 4: 
                    eventType = new Workshop();
                    break;
            }

            String[] argsPlaces = new String[2];
            switch(modality){
                case 1:
                    System.out.println("Type the place:");
                    argsPlaces[0] = scanner.nextLine();
                break;
                case 2:
                    System.out.println("Type the plataform stream: ");
                    argsPlaces[0] = scanner.nextLine();
                break;
                case 3:
                    System.out.println("Type the plataform stream: ");
                    argsPlaces[0] = scanner.nextLine();
                    System.out.println("Type the place:");
                    argsPlaces[1] = scanner.nextLine();                    
                break;
            }

        
            Event newEvent = service.register(title, date, capacity, description, eventType, argsPlaces, modality);
            System.out.println("New event has been registred");
            newEvent.printInformation();
        }
        catch(Throwable ex){
            System.out.println("Erro in register a event: " + ex.getMessage());
        }
    }

    public static void registerPersonEvent(PersonService personService, EventService eventService){
        HashSet<Person> hashSetPerson = personService.getAll();
        HashSet<Event> hashSetEvent = eventService.getAll();
        if(hashSetPerson.size() == 0){
            System.out.println("You must to register an person");
            return;
        }
        if(hashSetEvent.size() == 0){
            System.out.println("You must to register a event");
            return;
        }

        System.out.println("Type the person code that you want to add: ");
        printAll(hashSetPerson);
        long personId =  Long.parseLong(scanner.nextLine());
        System.out.println("Type the event code that you want to add: ");
        printAll(hashSetEvent);
        long eventId =  Long.parseLong(scanner.nextLine());

        try{
            Event event = (Event)eventService.getById(eventId);
            Person person = (Person)personService.getById(personId);

            if(!(event instanceof InPersonEvent)){
                System.out.println("Does the person have access to an internet-enabled device? 1 - Yes/2 - No");
                if(getOption(1,2) == 2);
                System.out.println("The person can't be registred in a online or hybrid event");
                return;
            }
            
            System.out.println("Is the person an participant or a speaker?");
            System.out.println("1 - Participant");
            System.out.println("2 - Speaker");
            int option = getOption(1, 2);

            if(option == 1){
                if(!(person instanceof Student)){
                    System.out.println("Only students can be an participant");
                    return;
                }
                eventService.addParticipantEvent(person, event);
            }
            else{
                eventService.addSpeakerEvent(person, event);
            }

            personService.addEventPerson(person, event);
            System.out.println(person.getName() + " has been registred in " + event.getName());
        }
        catch(Throwable ex){
            System.out.println("Erro in register an person in a event: " + ex.getMessage());
        }

    } 

    public static void generateCertificate(PersonService personService, EventService eventService){
        HashSet<Person> hashSetPerson = personService.getAll();
        HashSet<Event> hashSetEvent = eventService.getAll();
        try{
            if(hashSetPerson.size() == 0){
                System.out.println("You must to register an person");
                return;
            }
            if(hashSetEvent.size() == 0){
                System.out.println("You must to register a event");
                return;
            }
            System.out.println("Type the person code that you want to generate: ");
            printAll(hashSetPerson);
            long personId =  Long.parseLong(scanner.nextLine());
            Person person = (Person)personService.getById(personId);
            if(!(person instanceof Student)){
                System.out.println("Only students can generate certicate");
                return;
            }
            HashSet<Event> personEvents = personService.getAllEventsPerson(personId);
            if(personEvents.size() == 0){
                System.out.println("This person doesn't have events");
                return;
            }
            System.out.println("Type the event code that you want to generate: ");
            printAll(personEvents);
            long eventId =  Long.parseLong(scanner.nextLine());
            Event event = (Event)eventService.getById(eventId);
            System.out.println("---------------------------");
            System.out.println();
            System.out.println(person.getName() + " attended the " + event.getName() + "event on " + event.getDate());
            System.out.println();
            System.out.println("---------------------------");
        }
         catch(Throwable ex){
            System.out.println("Erro in generate certifacte: " + ex.getMessage());
        }
    }

    public static void eventDashboard(EventService service){
        HashSet<Event> hashSetEvent = service.getAll();
        if(hashSetEvent.size() == 0){
            System.out.println("You must to register a event");
            return;
        }
        boolean exit = false;
        while(!exit){
            clearConsole();
            printAll(hashSetEvent);
            System.out.println("Filter by: ");
            System.out.println("1 - Date");
            System.out.println("2 - Type");
            System.out.println("3 - Exit");
            int option = getOption(1, 3);
            switch (option) {
                case 1:
                    System.out.print("Type the date in this format MM/dd/yyyy: ");
                    String stringDate = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate date = LocalDate.parse(stringDate, formatter);
                    Set<IIdentifiedEntity> eventsByDate = hashSetEvent.stream()
                        .filter(e -> e.getDate().equals(date))
                        .collect(Collectors.toSet());
                    printAll(new HashSet<>(eventsByDate));
                    break;         
                case 2:
                    System.out.print("Type the type: ");
                    String type = scanner.nextLine();
                    Set<IIdentifiedEntity> eventsByType = hashSetEvent.stream()
                        .filter(e -> e.getType().equals(type))
                        .collect(Collectors.toSet());
                    printAll(new HashSet<>(eventsByType));
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }

    }

    private static int getOption(int minOption, int maxOption){ 
        int option;

        while(true){
            try {
            option = Integer.parseInt(scanner.nextLine());
            if(option >= minOption && option <= maxOption){
                return option;
            }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid option. Type it again");
        }      
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}