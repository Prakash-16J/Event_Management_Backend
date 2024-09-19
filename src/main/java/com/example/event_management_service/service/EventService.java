package com.example.event_management_service.service;

import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.Domain.Guest;
import com.example.event_management_service.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private TwilioService twilioService;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public Event getEventById(Long id){
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()){
            return existingEvent.get();
        }
        else throw new RuntimeException("Event Not Found");

    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id, Event updatedEvent){
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()){
            Event existingEvent = existingEventOpt.get();

            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setName(updatedEvent.getName());
            existingEvent.setBudget(updatedEvent.getBudget());
            existingEvent.setLocation(updatedEvent.getLocation());

            return eventRepository.save(existingEvent);
        }
        else throw new RuntimeException("Event not found with Id" + id);
    }

    public void deleteEvent(Long id){
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()){
            Event event = existingEvent.get();
            eventRepository.delete(event);
        }
        else throw new RuntimeException("Event Not Found");

    }

    public void sendSmsToGuestList(Long eventId, String messageBody) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        for (Guest guest : event.getGuests()) {
            twilioService.sendSms(guest.getPhone(), messageBody);
        }
    }

}
