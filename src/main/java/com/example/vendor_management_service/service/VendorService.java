package com.example.vendor_management_service.service;

import com.example.vendor_management_service.domain.Vendor;
import com.example.vendor_management_service.reporistory.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VendorService {

    @Autowired
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor addVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }
    public Optional<Vendor> getVendorById(Long id){
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if (vendor.isPresent())
            return vendor;
        else throw new RuntimeException("Vendor not found");
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


    public boolean isVendorAvailable(Long vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);
         if(vendor.isPresent()){
             return vendor.get().isAvailable();
         }
         throw new RuntimeException("Vendor not found");

        // Assuming Vendor entity has an 'available' field
//        return vendor.get().isAvailable();
    }

}
