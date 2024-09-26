package com.example.event_management_service.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vendor {
    @Id
    private Long id; // Vendor ID
    private String name; // Vendor name
    private boolean available; // Vendor availability
    private String serviceType; // e.g., "Photography", "Catering", etc.
    private String contactInfo;
    private double rating;


    @ManyToOne
    @JoinColumn(name = "event_id") // This creates the foreign key
    @JsonBackReference
    private Event event;
}
