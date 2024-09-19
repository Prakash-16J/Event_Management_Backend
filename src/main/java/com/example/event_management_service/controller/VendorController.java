package com.example.event_management_service.controller;

import com.example.event_management_service.Domain.Vendor;
import com.example.event_management_service.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor newVendor = vendorService.addVendor(vendor);
        return  ResponseEntity.ok(newVendor);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vendor>> getVendorById(@PathVariable Long id) {
        Optional<Vendor> vendor = vendorService.getVendorById(id);
        return vendor.map(value -> ResponseEntity.ok(vendor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendorDetails) {
        Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
        return ResponseEntity.ok(updatedVendor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Vendor>> getVendorsByCategory(@PathVariable String category) {
        List<Vendor> vendors = vendorService.getVendorsByCategory(category);
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @PostMapping("/events/{eventId}/vendors/{vendorId}")
    public ResponseEntity<Vendor> assignVendorToEvent(@PathVariable Long eventId, @PathVariable Long vendorId) {
        Vendor vendor = vendorService.addVendorToEvent(eventId, vendorId);
        return  ResponseEntity.ok(vendor);
    }

}
