package com.example.vendor_management_service.exceptiom;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String vendorNotFound) {
    }
}
