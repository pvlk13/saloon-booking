package com.vijaya.service_offering.controller;

import com.vijaya.service_offering.modal.ServiceOffering;
import com.vijaya.service_offering.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceController {
    @Autowired
    private ServiceOfferingService serviceOfferingService;

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long serviceId) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingService.getServiceById(serviceId);
        return ResponseEntity.ok(serviceOffering);
    }
    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getAllServicesBySalonId(@PathVariable Long salonId,
                                                                        @RequestParam(required = false) Long categoryId)
            throws Exception {
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServicesBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOfferings);
    }
    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) throws Exception {
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok( serviceOfferings);
    }
    @GetMapping("/services")
    public ResponseEntity<Set<ServiceOffering>> getAllServices() throws Exception {
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServices();
        return ResponseEntity.ok(serviceOfferings);
    }

}
