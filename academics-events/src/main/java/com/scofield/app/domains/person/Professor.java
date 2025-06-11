package com.scofield.app.domains.person;

import java.util.ArrayList;

public class Professor extends Person{
    private ArrayList<String> subjects;
    
    public Professor(String name, String cpf, ArrayList<String> subjects){
        super(name, cpf);
        validateSubjects(subjects);
        this.subjects = subjects;
    }

    private void validateSubjects(ArrayList<String> subjects){
        if(subjects.size() == 0 || subjects == null){
                throw new IllegalArgumentException("Subjects can't be null or empty");
        }
    }

    public ArrayList<String> getSubjects(){
        return this.subjects;
    }

    public void printInformation(){
        System.out.println("Professor: ");
        super.printInformation();
        System.out.println("Subjects:");
        for(String subject : subjects) {
            System.out.println("- " + subject);
        }
        System.out.println("------------------");
    }
}