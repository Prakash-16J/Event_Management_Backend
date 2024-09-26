package com.example.event_management_service.Domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class VendorDto {

    private Long id; // Vendor ID
    private String name; // Vendor name
    private boolean available; // Vendor availabity
    private String serviceType; // e.g., "Photography", "Catering", etc.
    private String contactInfo;
    private double rating;
}
