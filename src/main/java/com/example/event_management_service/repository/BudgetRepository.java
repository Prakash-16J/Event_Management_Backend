package com.example.event_management_service.repository;

import com.example.event_management_service.Domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Budget findByEventId(Long eventId);

}
