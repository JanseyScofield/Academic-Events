package com.scofield.app.domains.event;

import java.time.LocalDate;

import com.scofield.app.domains.event.eventsTypes.IEventType;

public class OnlineEvent extends Event{
    protected String platformStream;
    protected String link;

    public OnlineEvent(String title, LocalDate date, int capacity, String description, String platformStream, IEventType eventType){
        super(title, date, capacity, description, eventType);
        validatePlataformStream(platformStream);
        this.platformStream = platformStream;
        this.link = "www." + platformStream.toLowerCase() + ".com/" + id;
    }

    private void validatePlataformStream(String platformStream){
         if(platformStream.isEmpty() || platformStream == null){
            throw new IllegalArgumentException("Platform Stream can't be null or empty");
        }
    }

    public String getPlatformStream() {
        return platformStream;
    }

    public String getLink() {
        return link;
    }
    
    public void printInformation(){
        System.out.println("Online Event: ");
        super.printInformation();
        System.out.println("Plataform: " + this.platformStream);
        System.out.println("Link: " + this.link);
        System.out.println("------------------");
    }
}