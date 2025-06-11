package com.scofield.app.services;

import java.time.LocalDate;
import java.util.HashSet;

import com.scofield.app.domains.event.Event;
import com.scofield.app.domains.person.Person;
import com.scofield.app.domains.event.EventRepository;
import com.scofield.app.domains.event.HybridEvent;
import com.scofield.app.domains.event.InPersonEvent;
import com.scofield.app.domains.event.OnlineEvent;
import com.scofield.app.domains.event.eventsTypes.IEventType;

public class EventService extends Service<Event>{
     public EventService(EventRepository repository ){
        super(repository);
    }

    public Event register(String title, LocalDate date, int capacity, String description, IEventType eventType, String[] placeArgs, int modality){
        try{
            Event newEvent;
            if(modality == 1){
                newEvent = new InPersonEvent(title, date, capacity, description, placeArgs[0], eventType);
            }
            else if(modality == 2){
                newEvent = new OnlineEvent(title, date, capacity, description, placeArgs[0], eventType);
            }
            else if(modality == 3){
                newEvent = new HybridEvent(title, date, capacity, description, placeArgs[0], placeArgs[1], eventType);
            }
            else{
                throw new IllegalArgumentException("Invalid params to register a event");
            }
            repository.register(newEvent);
            return newEvent;
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Error to register a event: " + ex.getMessage());
        }       
    }

    public HashSet<Person> getAllSpeakersEvent(long eventId){
        Event event = (Event)repository.getById(eventId);
        if(event == null){
            throw new IllegalArgumentException("Event doesn't exist");
        }
        return event.getSpeakers();
    }

    public HashSet<Person> getAllParticpantsEvent(long eventId){
        Event event = (Event)repository.getById(eventId);
        if(event == null){
            throw new IllegalArgumentException("Event doesn't exist");
        }
        return event.getParticipants();
    }
}
