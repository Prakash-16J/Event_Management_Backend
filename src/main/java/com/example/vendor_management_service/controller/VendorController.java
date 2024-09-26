package com.example.vendor_management_service.controller;

import com.example.vendor_management_service.domain.Vendor;
import com.example.vendor_management_service.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    // Create a new vendor
    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor newVendor = vendorService.addVendor(vendor);
        return ResponseEntity.ok(newVendor);
    }

    // Get all vendors
    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    // Get vendor by ID
    @GetMapping("/{id}")
    public Optional<Vendor> getVendorById(@PathVariable Long id) {
            Optional<Vendor> vendor = vendorService.getVendorById(id);
            return vendor;
    }

    // Update a vendor
    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendorDetails) {
        Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
        return ResponseEntity.ok(updatedVendor);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Vendor>> getVendorsByCategory(@PathVariable String category) {
        List<Vendor> vendors = vendorService.getVendorsByCategory(category);
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    // Delete a vendor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{vendorId}/availability")
    public ResponseEntity<Boolean> checkVendorAvailability(@PathVariable Long vendorId) {
        boolean isAvailable = vendorService.isVendorAvailable(vendorId);
        return ResponseEntity.ok(isAvailable);
    }

//    @PostMapping("/events/{eventId}/vendors/{vendorId}")
//    public ResponseEntity<Vendor> assignVendorToEvent(@PathVariable Long eventId, @PathVariable Long vendorId) {
//        Vendor vendor = vendorService.addVendorToEvent(eventId, vendorId);
//        return  ResponseEntity.ok(vendor);
//    }
}
