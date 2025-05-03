package com.vijaya.service_offering.service;

import com.vijaya.service_offering.modal.ServiceOffering;
import com.vijaya.service_offering.payload.dto.CategoryDTO;
import com.vijaya.service_offering.payload.dto.SalonDTO;
import com.vijaya.service_offering.payload.dto.ServiceDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface ServiceOfferingService {
     ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);
     Set<ServiceOffering> getAllServices();
     ServiceOffering updateServiceOffering(Long serviceId, ServiceDTO serviceDTO) throws Exception;
     Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId) throws Exception;
     Set<ServiceOffering> getServicesByIds(Set<Long> ids) throws Exception;
     ServiceOffering getServiceById(Long serviceId) throws Exception;

}
