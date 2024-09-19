package com.example.event_management_service.service;

import com.example.event_management_service.Domain.Event;
import com.example.event_management_service.Domain.Vendor;
import com.example.event_management_service.exception.ResourceNotFoundException;
import com.example.event_management_service.repository.EventRepository;
import com.example.event_management_service.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private final VendorRepository vendorRepository;
    private final EventRepository eventRepository;

    public VendorService(VendorRepository vendorRepository, EventRepository eventRepository) {
        this.vendorRepository = vendorRepository;
        this.eventRepository = eventRepository;
    }

    public Vendor addVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }
    public Optional<Vendor> getVendorById(Long id){
        return vendorRepository.findById(id);
    }

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Optional<Vendor> vendorOpt =  vendorRepository.findById(id);
        if (vendorOpt.isPresent()) {
            Vendor vendor = vendorOpt.get();
            vendor.setName(vendorDetails.getName());
            vendor.setServiceType(vendorDetails.getServiceType());
            vendor.setContactInfo(vendorDetails.getContactInfo());
            vendor.setRating(vendorDetails.getRating());
            return vendorRepository.save(vendor);
        }
        else throw new RuntimeException("Vendor not found");
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
    public List<Vendor> getVendorsByCategory(String category) {
        return vendorRepository.findByServiceType(category);
    }

    public Vendor addVendorToEvent(Long eventId, Long vendorId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        event.getVendors().add(vendor);
        return vendorRepository.save(vendor);
    }

}
