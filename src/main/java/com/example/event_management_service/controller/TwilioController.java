package com.example.event_management_service.controller;


import com.example.event_management_service.service.EventService;
import com.example.event_management_service.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class TwilioController {


    @Autowired
    private EventService eventService;

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/{eventId}/send-sms")
    public String sendSmsToGuests(@PathVariable Long eventId, @RequestParam String message) {
        try {
            eventService.sendSmsToGuestList(eventId, message);
            return "Messages sent successfully";
        } catch (Exception e) {
            return "Failed to send messages: " + e.getMessage();
        }
    }
}
