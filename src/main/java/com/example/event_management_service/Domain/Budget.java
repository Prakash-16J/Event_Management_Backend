package com.example.event_management_service.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double totalAmount = 0.0; // Initialize total amount

    @OneToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    @JsonBackReference
    private Event event;

}
