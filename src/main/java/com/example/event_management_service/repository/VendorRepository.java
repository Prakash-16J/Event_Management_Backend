package com.example.event_management_service.repository;

import com.example.event_management_service.Domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
    List<Vendor> findByServiceType(String serviceType);
}
