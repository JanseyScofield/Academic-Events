package com.scofield.app;

import com.scofield.app.domains.person.PersonRepository;
import com.scofield.app.services.person.PersonService;
import com.scofield.app.ui.Ui;

public class App {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository();
        PersonService personService = new PersonService(personRepository);

        boolean finishProgram = false;
        while (!finishProgram) {
            int optionMain = Ui.manageMainMenu();
            switch (optionMain) {
                case 1:
                    boolean exitEventModule = false;
                    while(!exitEventModule){
                        int optionEvent = Ui.managePersonMenu();
                        switch(optionEvent){
                            case 1:
                                Ui.printAll(personService);
                                break;
                            case 2: 
                                break;
                            case 3:
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
                                Ui.printAll(personService);
                                break;
                            case 2: 
                                Ui.registerPerson(personService);
                                break;
                            case 3:
                                break;
                            case 4: 
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
