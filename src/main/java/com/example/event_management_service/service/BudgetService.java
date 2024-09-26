package com.example.event_management_service.service;


import com.example.event_management_service.Domain.Budget;
import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.repository.BudgetRepository;
import com.example.event_management_service.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private final BudgetRepository budgetRepository;

    @Autowired
    private final EventRepository eventRepository;


    public BudgetService(BudgetRepository budgetRepository, EventRepository eventRepository) {
        this.budgetRepository = budgetRepository;
        this.eventRepository = eventRepository;
    }

    public Budget createBudget(Long eventId, Double totalAmount) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Budget budget = new Budget();
        budget.setEvent(event);
        budget.setTotalAmount(totalAmount);
        return budgetRepository.save(budget);
    }

    public Budget getBudgetByEventId(Long eventId) {
        return budgetRepository.findByEventId(eventId);
    }

    public Budget addCostToBudget(Long eventId, Double cost) {
        Budget budget = getBudgetByEventId(eventId);
        if (budget != null) {
            budget.setTotalAmount(budget.getTotalAmount() + cost);
            return budgetRepository.save(budget);
        }
        throw new RuntimeException("Budget not found for event ID: " + eventId);
    }

    public Double getTotalSpent(Long eventId) {
        Budget budget = getBudgetByEventId(eventId);
        return budget != null ? budget.getTotalAmount() : 0.0;
    }

    public void updateBudget(Long eventId, Double totalAmount) {
        Budget budget = getBudgetByEventId(eventId);
        if (budget != null) {
            budget.setTotalAmount(totalAmount);
            budgetRepository.save(budget);
        }
    }

    public void deleteBudget(Long eventId) {
        Budget budget = getBudgetByEventId(eventId);
        if (budget != null) {
            budgetRepository.delete(budget);
        }
    }
}
