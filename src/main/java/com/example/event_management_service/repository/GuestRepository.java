package com.example.event_management_service.repository;

import com.example.event_management_service.Domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long> {
    List<Guest> findByEventId(Long eventId);
}
