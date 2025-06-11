package com.scofield.app.domains.event;

import java.time.LocalDate;
import java.util.HashSet;

import com.scofield.app.domains.interfaces.IIdentifiedEntity;
import com.scofield.app.domains.person.Person;

public class Event implements IIdentifiedEntity{
    private static long eventAmount;      
    protected long id;
    protected String title;
    protected LocalDate date;
    protected String place;
    protected HashSet<Person> participants = new HashSet<Person>();
    protected HashSet<Person> speakers = new HashSet<Person>();
    protected int capacity;

    protected String description;
    
    public Event(String title, LocalDate date, String place, int capacity, String description){
        validate(title, date, place, capacity, description);

        this.id = ++eventAmount;
        this.title = title;
        this.date = date;
        this.place = place;
        this.capacity = capacity;
        this.description = description;
    }

    private void validate(String title, LocalDate date, String place, int capacity, String description){
        if(title.isEmpty() || title == null){
            throw new IllegalArgumentException("Title can't be null or empty");
        }
        if(date == null){
            throw new IllegalArgumentException("Date can't be null or empty");
        }
        if(place.isEmpty() || place == null){
            throw new IllegalArgumentException("Place can't be null or empty");
        }
        if(capacity < 2){
            throw new IllegalArgumentException("Capacity can´t be less than two");
        }
        if(description.isEmpty() || description == null){
            throw new IllegalArgumentException("Description can't be null or empty");
        }
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.title;
    }

    public void printInformation(){
        System.out.println("------------------");
        System.out.println("Code: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Date: " + this.date);
        System.out.println("Capacity: " + this.capacity);

        System.out.println("Speakers: ");
        if(speakers.size() == 0){
            System.out.println("There are no registered speakers");
        }
        else{
            for(Person speaker : speakers){
                speaker.printInformation();
            }
        }

        System.out.println("Participants: ");
        if(participants.size() == 0){
            System.out.println("There are no participant speakers");
        }
        else{
            for(Person participant : participants){
                participant.printInformation();
            }
        }
    }

    public Person addSpeaker(Person speaker){
        if(speaker == null){
            throw new IllegalArgumentException("Speaker can't be null");
        }
        this.speakers.add(speaker);
        return speaker; 
    }

    public Person addParticipant(Person participant){
        if(participant == null){
            throw new IllegalArgumentException("Participant can't be null");
        }
        this.participants.add(participant);
        return participant; 
    } 
}