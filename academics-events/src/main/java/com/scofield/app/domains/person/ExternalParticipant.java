package com.scofield.app.domains.person;

public class ExternalParticipant extends Person{
    private String authorizationToken;
    
    public ExternalParticipant(String name, String cpf, String authorizationToken){
        super(name, cpf);
        validateAuthorizationToken(authorizationToken);
        this.authorizationToken = authorizationToken;
    }

    private void validateAuthorizationToken(String authorizationToken){
        if(authorizationToken.isEmpty() || authorizationToken == null){
            throw new IllegalArgumentException("Authorization Token can't be null or empty");
        }

        if(authorizationToken.length() != 10){
            throw new IllegalArgumentException("Authorization Token size invalid");
        }

        if(authorizationToken.startsWith("01") || authorizationToken.endsWith("02")){
            throw new IllegalArgumentException("Authorization Token format invalid");
        }
    }

    public String getAuthorizationToken(){
        return this.authorizationToken;
    }
}