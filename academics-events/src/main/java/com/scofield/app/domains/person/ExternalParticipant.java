package com.scofield.app.domains.person;

public class ExternalParticipant extends Person{
    private Integer authorizationToken;
    
    public ExternalParticipant(String name, String cpf, Integer authorizationToken){
        super(name, cpf);
        validateAuthorizationToken(authorizationToken);
        this.authorizationToken = authorizationToken;
    }

    private void validateAuthorizationToken(int authorizationToken){
        if(authorizationToken <= 0 || authorizationToken > 2000){
            throw new IllegalArgumentException("Authorization token invalid");
        }
    }

    public Integer getAuthorizationToken(){
        return this.authorizationToken;
    }

    public void printInformation(){
        System.out.println("External Participant: ");
        super.printInformation();
        System.out.println("Authorization Token: " + this.authorizationToken);
        System.out.println("------------------");
    }
}