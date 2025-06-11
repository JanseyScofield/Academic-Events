package com.scofield.app.domains.event;

import java.time.LocalDate;

import com.scofield.app.domains.event.eventsTypes.IEventType;

public class InPersonEvent extends Event{
    protected String place;

    public InPersonEvent(String title, LocalDate date, int capacity, String description, String place, IEventType eventType){
        super(title, date, capacity, description, eventType);
        validatePlace(place);
        this.place = place;
    }

    private void validatePlace(String place){
        if(place.isEmpty() || place == null){
            throw new IllegalArgumentException("Platform Stream can't be null or empty");
        }
    }

    public String getPlace() {
        return place;
    }

    public void printInformation(){
        System.out.println("In Person Event: ");
        super.printInformation();
        System.out.println("Place: "  + this.place);
        System.out.println("------------------");
    }
}