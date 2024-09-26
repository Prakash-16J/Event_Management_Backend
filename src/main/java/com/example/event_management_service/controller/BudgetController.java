package com.example.event_management_service.controller;

import com.example.event_management_service.Domain.Budget;
import com.example.event_management_service.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetController {


        private final BudgetService budgetService;

        public BudgetController(BudgetService budgetService) {
            this.budgetService = budgetService;
        }

        @PostMapping
        public ResponseEntity<String> createBudget(@RequestParam Long eventId, @RequestParam Double totalAmount) {
//            Budget createdBudget = budgetService.createBudget(budget.getEventId(), budget.getTotalAmount());
//            return ResponseEntity.ok(createdBudget);

            Budget createdBudget = budgetService.createBudget(eventId, totalAmount);
                return ResponseEntity.ok("Budget added to event successfully.");

        }


        @GetMapping("/{eventId}")
        public ResponseEntity<Budget> getBudgetByEventId(@PathVariable Long eventId) {
            Budget budget = budgetService.getBudgetByEventId(eventId);
            return ResponseEntity.ok(budget);
        }

        @PostMapping("/{eventId}/addCost")
        public ResponseEntity<Budget> addCost(@PathVariable Long eventId, @RequestParam Double cost) {
            Budget updatedBudget = budgetService.addCostToBudget(eventId, cost);
            return ResponseEntity.ok(updatedBudget);
        }

        @GetMapping("/{eventId}/totalSpent")
        public ResponseEntity<Double> getTotalSpent(@PathVariable Long eventId) {
            Double totalSpent = budgetService.getTotalSpent(eventId);
            return ResponseEntity.ok(totalSpent);
        }

        @PutMapping("/{eventId}")
        public ResponseEntity<Void> updateBudget(@PathVariable Long eventId, @RequestBody Double totalAmount) {
            budgetService.updateBudget(eventId, totalAmount);
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("/{eventId}")
        public ResponseEntity<Void> deleteBudget(@PathVariable Long eventId) {
            budgetService.deleteBudget(eventId);
            return ResponseEntity.ok().build();
        }
}
