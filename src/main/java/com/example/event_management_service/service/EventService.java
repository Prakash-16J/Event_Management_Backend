package com.example.event_management_service.service;

import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.Domain.Guest;
import com.example.event_management_service.Domain.Vendor;
import com.example.event_management_service.Domain.VendorDto;
import com.example.event_management_service.exception.ResourceNotFoundException;
import com.example.event_management_service.feignclient.VendorClient;
import com.example.event_management_service.repository.EventRepository;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private VendorClient vendorClient;

    @Autowired
    private TwilioService twilioService;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            return existingEvent.get();
        } else throw new RuntimeException("Event Not Found");

    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();

            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setName(updatedEvent.getName());
            existingEvent.setBudget(updatedEvent.getBudget());
            existingEvent.setLocation(updatedEvent.getLocation());

            return eventRepository.save(existingEvent);
        } else throw new RuntimeException("Event not found with Id" + id);
    }

    public void deleteEvent(Long id) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            eventRepository.delete(event);
        } else throw new RuntimeException("Event Not Found");

    }

    public void sendSmsToGuestList(Long eventId, String messageBody) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        for (Guest guest : event.getGuests()) {
            twilioService.sendSms(guest.getPhone(), messageBody);
        }
    }

//    public Vendor addVendorToEvent(Long eventId, Long vendorId) {
//        logger.info("Attempting to add vendor with ID {} to event with ID {}", vendorId, eventId);
//        // Fetch the event
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> {
//                    logger.error("Event with ID {} not found", eventId);
//                    return new ResourceNotFoundException("Event not found");
//                });
//
//        // Check if the vendor is available
//
//        logger.info("Fetching vendor with ID {}", vendorId);
//        Optional<VendorDto> vendorDtoOptional = vendorClient.getVendorById(vendorId);
//        if (vendorDtoOptional.isEmpty()) {
////
//            logger.error("Vendor with ID {} not found", vendorId);
//            throw new RuntimeException("Vendor with ID " + vendorId + " not found");
//
//        // Fetch full vendor details from the vendor service
////
//        VendorDto vendorDto = vendorDtoOptional.get();
//        logger.info("Vendor found: {}", vendorDto);
//
//        // Convert VendorDTO to Vendor entity
//        Vendor vendor = new Vendor();
//        vendor.setId(vendorDto.getId());
//        vendor.setName(vendorDto.getName());
//        vendor.setServiceType(vendorDto.getServiceType());
//        vendor.setContactInfo(vendorDto.getContactInfo());
//        vendor.setRating(vendorDto.getRating());
//
//        // Add vendor to the event
//        event.getVendors().add(vendor); // Assuming Event has a List<Vendor>
//        logger.info("Adding vendor {} to event {}", vendor.getName(), event.getId());
//
//
//        // Save the event with the vendor added
//        eventRepository.save(event);
//        logger.info("Vendor {} successfully added to event {}", vendor.getName(), event.getId());
//
//        return vendor;
//
//    }

    public Vendor addVendorToEvent(Long eventId, Long vendorId) {
        // Fetch the event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        //Check if the vendor is available
        Boolean isAvailable = vendorClient.checkVendorAvailability(vendorId);
        if (!isAvailable) {
            throw new RuntimeException("Vendor is not available for this event");
        }

        // Fetch full vendor details from the vendor service
        Vendor vendor = vendorClient.getVendorDetails(vendorId);




            // Add vendor to the event
            event.getVendors().add(vendor); // Assuming Event has a List<Vendor>
            vendor.setEvent(event);


        // Save the event with the vendor added
            eventRepository.save(event);

            return vendor;

        }
}


//public Vendor addVendorToEvent(Long eventId, Long vendorId) {
////    Fetch the event
//    Event event = eventRepository.findById(eventId)
//            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
//
//    //Check if the vendor is available
//       Boolean isAvailable = vendorClient.checkVendorAvailability(vendorId);
//       if (!isAvailable) {
//           throw new RuntimeException("Vendor is not available for this event");
//        }
//
//
//
////     Fetch full vendor details from the vendor service
//        Vendor vendor = vendorClient.getVendorDetails(vendorId);
//
//
//
//
//    // Add vendor to the event
//    event.getVendors().add(vendor); // Assuming Event has a List<Vendor>
//
//
//
//    // Save the event with the vendor added
//    eventRepository.save(event);
//
//    return vendor;
//
//}