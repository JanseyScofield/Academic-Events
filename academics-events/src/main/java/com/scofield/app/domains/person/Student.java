package com.scofield.app.domains.person;

public class Student extends Person{
    private String registrationNumber;
    
    public Student(String name, String cpf, String registrationNumber){
        super(name, cpf);
        validateRegistrationNumber(registrationNumber);
        this.registrationNumber = registrationNumber;
    }

    private void validateRegistrationNumber(String registrationNumber){
        if(registrationNumber.isEmpty() || registrationNumber == null){
                throw new IllegalArgumentException("Registration number can't be null or empty");
        }
    }

    public String getRegistrationNumber(){
        return this.registrationNumber;
    }

    public void printInformation(){
        System.out.println("Student:");
        super.printInformation();
        System.out.println("Registration Number: " + this.registrationNumber);
        System.out.println("------------------");
    }
}