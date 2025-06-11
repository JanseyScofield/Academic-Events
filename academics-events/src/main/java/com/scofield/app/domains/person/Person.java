package com.scofield.app.domains.person;

import com.scofield.app.domains.interfaces.IIdentifiedEntity;

public class Person implements IIdentifiedEntity{
    private static long peopleAmount;
    protected long id;
    protected String name;
    protected String cpf;  
    
    public  Person(String name, String cpf){
        validate(name, cpf);

        this.id = ++peopleAmount;
        this.name = name;
        this.cpf = cpf;
    }

    private void validate(String name, String cpf){
        if(name.isEmpty() ||  name == null){
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        if(!validateCpf(cpf)){
            throw new IllegalArgumentException("Invalid CPF");
        }
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void printInformation(){
        System.out.println("------------------");
        System.out.println("Code: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("CPF: " + this.cpf);
    }
    
    public static boolean validateCpf(String cpf) {
        if (cpf == null || !(cpf.length() == 11)) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma1 % 11);
        if (digito1 >= 10) digito1 = 0;

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma2 % 11);
        if (digito2 >= 10) digito2 = 0;
            
        return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;
    }
}