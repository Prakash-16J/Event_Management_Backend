package com.example.event_management_service.service;

import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.Domain.Guest;
import com.example.event_management_service.repository.EventRepository;
import com.example.event_management_service.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    public GuestService(EventRepository eventRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }


    public void addGuestToEvent(Long eventId, Guest guest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.addGuest(guest); // Add guest to event
        guestRepository.save(guest); // Save guest to the database
    }

    public List<Guest> getGuestsByEvent(Long eventId) {
        return guestRepository.findByEventId(eventId);
    }


}
