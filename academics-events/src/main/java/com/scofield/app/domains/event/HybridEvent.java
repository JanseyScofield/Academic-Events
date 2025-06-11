package com.scofield.app.domains.event;

import java.time.LocalDate;

import com.scofield.app.domains.event.eventsTypes.IEventType;

public class HybridEvent extends Event{
    protected String place;
    protected String platformStream;
    protected String link = "www." + platformStream + ".com/" + title;

    public HybridEvent(String title, LocalDate date, int capacity, String description, String platformStream, String place, IEventType eventType){
        super(title, date, capacity, description, eventType);
        validatePlataformStreamPlace(platformStream, place);
        this.platformStream = platformStream;
    }

    public void validatePlataformStreamPlace(String platformStream, String place){
         if(platformStream.isEmpty() || platformStream == null){
            throw new IllegalArgumentException("Platform Stream can't be null or empty");
        }
        if(place.isEmpty() || place == null){
            throw new IllegalArgumentException("Platform Stream can't be null or empty");
        }
    }

    public String getPlatformStream() {
        return platformStream;
    }

    public String getLink() {
        return link;
    }

    public String getPlace() {
        return place;
    }

    public void printInformation(){
        System.out.println("Hybrid Event: ");
        super.printInformation();
        System.out.println("Plataform: " + this.platformStream);
        System.out.println("Link: " + this.link);
        System.out.println("Place: "  + this.place);
        System.out.println("------------------");
    }
}
