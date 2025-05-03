package com.vijaya.service_offering.controller;

import com.vijaya.service_offering.modal.ServiceOffering;
import com.vijaya.service_offering.payload.dto.CategoryDTO;
import com.vijaya.service_offering.payload.dto.SalonDTO;
import com.vijaya.service_offering.payload.dto.ServiceDTO;
import com.vijaya.service_offering.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class ServiceSalonController {
    @Autowired
    private ServiceOfferingService serviceOfferingService;
    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());
        return ResponseEntity.ok(serviceOfferingService.createServiceOffering(salonDTO, serviceDTO, categoryDTO));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) throws Exception {
        return ResponseEntity.ok(serviceOfferingService.updateServiceOffering(id, serviceDTO));
    }
}
