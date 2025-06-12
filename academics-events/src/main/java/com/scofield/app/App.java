package com.scofield.app;

import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.services.EventService;
import com.scofield.app.services.PersonService;
import com.scofield.app.domains.event.EventRepository;
import com.scofield.app.domains.person.Person;
import com.scofield.app.domains.event.Event;
import com.scofield.app.ui.Ui;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        Ui.clearConsole();
        PersonRepository personRepository = new PersonRepository();
        PersonService personService = new PersonService(personRepository);
        EventRepository eventRepository = new EventRepository();
        EventService eventService = new EventService(eventRepository);

        boolean finishProgram = false;
        while (!finishProgram) {
            int optionMain = Ui.manageMainMenu();
            switch (optionMain) {
                case 1:
                    boolean exitEventModule = false;
                    while(!exitEventModule){
                        int optionEvent = Ui.manageEventMenu();
                        switch(optionEvent){
                            case 1:
                                HashSet<Event> hashSetEvent = eventService.getAll();
                                Ui.printAll(hashSetEvent);
                                break;
                            case 2:
                                Ui.eventDashboard(eventService);
                                break;
                            case 3:
                                Ui.registerEvent(eventService);
                                break;
                            case 4:
                                Ui.clearConsole();
                                exitEventModule = true;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean exitPersonModule = false;
                    while(!exitPersonModule){
                        int optionPerson = Ui.managePersonMenu();
                        switch(optionPerson){
                            case 1:
                                HashSet<Person> hashSetPerson = personService.getAll();
                                Ui.printAll(hashSetPerson);
                                break;
                            case 2: 
                                Ui.registerPerson(personService);
                                break;
                            case 3:
                                Ui.generateCertificate(personService, eventService);
                                break;
                            case 4:
                                Ui.registerPersonEvent(personService, eventService);
                                break;
                            case 5:
                                Ui.clearConsole();
                                exitPersonModule = true;
                                break;
                        }
                    }
                    break;
                case 3:
                    finishProgram = true;
                    break;
            }
        }
    }
}
