package com.example.event_management_service.controller;


import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.service.EventService;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping()
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event event){
        Event updatedEvent = eventService.updateEvent(id,event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable long id){
       eventService.deleteEvent(id);
       return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/addVendor/{vendorId}")
    public ResponseEntity<String> addVendorToEvent(@PathVariable Long eventId, @PathVariable Long vendorId) {
        eventService.addVendorToEvent(eventId, vendorId);
        return ResponseEntity.ok("Vendor added to event successfully");
    }

}
