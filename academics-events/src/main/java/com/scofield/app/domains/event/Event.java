// package com.scofield.app.domains.event;

// import java.time.LocalDate;
// import java.util.HashMap;
// import com.scofield.app.domains.person;

// public class Event{
//     private static long eventAmount;      
//     protected long id;
//     protected String title;
//     protected LocalDate date;
//     protected String place;
//     protected HashMap<Int, Person> participants;
//     protected int capacity;

//     protected String description;
    
//     public Event(String title, LocalDate date, String place, int capacity, String description){
//         validate(title, date, place, capacity, description);

//         this.id = ++eventAmount;
//         this.title = title;
//         this.date = date;
//         this.place = place;
//         this.capacity = capacity;
//         this.description = description;
//     }

//     private void validate(String title, LocalDate date, String place, int capacity, String description){
//         if(title.isEmpty() || title == null){
//             throw new IllegalArgumentException("Title can't be null or empty");
//         }
//         if(date == null){
//             throw new IllegalArgumentException("Date can't be null or empty");
//         }
//         if(place.isEmpty() || place == null){
//             throw new IllegalArgumentException("Place can't be null or empty");
//         }
//         if(capacity < 2){
//             throw new IllegalArgumentException("Capacity can´t be less than two");
//         }
//         if(description.isEmpty() || description == null){
//             throw new IllegalArgumentException("Description can't be null or empty");
//         }
//     }

//     public long getId() {
//         return id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public LocalDate getDate() {
//         return date;
//     }
    
//     public int getCapacity() {
//         return capacity;
//     }
    
//     public String getDescription() {
//         return description;
//     }
// }