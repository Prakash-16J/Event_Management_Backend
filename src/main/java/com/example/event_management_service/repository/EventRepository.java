package com.example.event_management_service.repository;

import com.example.event_management_service.Domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
