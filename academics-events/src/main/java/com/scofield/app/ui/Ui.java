package com.scofield.app.ui;

import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import com.scofield.app.domains.person.Person;
import javax.imageio.IIOException;

import com.scofield.app.domains.interfaces.IIdentifiedEntity;
import com.scofield.app.services.abstratcs.Service;
import com.scofield.app.services.person.PersonService;

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

    public static void printAll(Service service){
        HashSet<IIdentifiedEntity> entities = service.getAll();
        if(entities.size() == 0){
            System.out.println("Don't have registred");
            return;
        }
        for(IIdentifiedEntity entity : entities){
            entity.printInformation();
        }
    }

    public static void registerPerson(PersonService service){
        System.out.println("What kind of person do you want to register?");
        System.out.println("1 - Student");
        System.out.println("2 - Professor");
        System.out.println("3 - External Participant");
        System.out.println("4 - Exit");
        int option = getOption(1,3);

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
                    System.out.println("Continue? 1 - Yes/ 2 - No");
                    exitAddSubjects = getOption(1, 2) == 2;
                }
                paramObject = listSubjects;
                break;
            case 3:
                System.out.println("Type registration number:");
                paramObject = (Integer)Integer.parseInt(scanner.nextLine());
                break;
            case 4:
                return;
        }

        try{
            Person newPerson = service.register(name, cpf, paramObject);
            System.out.println("New person has been registred");
            newPerson.printInformation();
        }
        catch(Throwable ex){
            System.out.println("Erro in register an person: " + ex.getMessage());
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