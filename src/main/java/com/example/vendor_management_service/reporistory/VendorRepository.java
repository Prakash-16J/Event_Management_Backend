package com.example.vendor_management_service.reporistory;

import com.example.vendor_management_service.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByServiceType(String serviceType);
}
