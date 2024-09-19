package com.example.event_management_service.controller;

import com.example.event_management_service.Domain.Guest;
import com.example.event_management_service.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/{eventId}/guests")
    public ResponseEntity<String> addGuestToEvent(@PathVariable Long eventId, @RequestBody Guest guest) {
        guestService.addGuestToEvent(eventId, guest);
        return ResponseEntity.ok("Guest added successfully.");
    }

    @GetMapping("/{eventId}/guests")
    public ResponseEntity<List<Guest>> getGuestsByEvent(@PathVariable Long eventId) {
        List<Guest> guests = guestService.getGuestsByEvent(eventId);
        return ResponseEntity.ok(guests);
    }

}
