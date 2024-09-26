package com.example.event_management_service.feignclient;

import com.example.event_management_service.Domain.Vendor;
import com.example.event_management_service.Domain.VendorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient( name = "vendor-management-service",url = "http://localhost:8091/Vendors")
public interface VendorClient {

    @GetMapping("/{vendorId}/availability")
    Boolean checkVendorAvailability(@PathVariable("vendorId") Long vendorId);


    @GetMapping("/{vendorId}")
    Vendor getVendorDetails(@PathVariable("vendorId") Long vendorId);

    @GetMapping("/{vendorId}")
    Optional<VendorDto> getVendorById(@PathVariable("vendorId") Long vendorId);
}
