package com.vijaya.service_offering.service.serviceImpl;

import com.vijaya.service_offering.modal.ServiceOffering;
import com.vijaya.service_offering.payload.dto.CategoryDTO;
import com.vijaya.service_offering.payload.dto.SalonDTO;
import com.vijaya.service_offering.payload.dto.ServiceDTO;
import com.vijaya.service_offering.repository.ServiceOfferingRepository;
import com.vijaya.service_offering.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class ServiceImpl implements ServiceOfferingService {
    @Autowired
    private ServiceOfferingRepository serviceOfferingRepository;
    @Override
    public  ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImages(serviceDTO.getImages());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setDuration(serviceDTO.getDuration());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOfferingRepository.save(serviceOffering);
        return serviceOffering;
    }

    @Override
    public Set<ServiceOffering> getAllServices() {
        List<ServiceOffering> serviceOfferings = serviceOfferingRepository.findAll();
        return new HashSet<>(serviceOfferings);
    }

    @Override
    public ServiceOffering updateServiceOffering(Long serviceId, ServiceDTO serviceDTO) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering != null){
            serviceOffering.setImages(serviceDTO.getImages());
            serviceOffering.setName(serviceDTO.getName());
            serviceOffering.setDescription(serviceDTO.getDescription());
            serviceOffering.setDuration(serviceDTO.getDuration());
            serviceOffering.setPrice(serviceDTO.getPrice());
            return serviceOfferingRepository.save(serviceOffering);
        }else {
            throw new Exception("ServiceId not found");
        }

    }

    @Override
    public Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId) throws Exception {
        Set<ServiceOffering> serviceOfferings = serviceOfferingRepository.findAllBySalonId(salonId);
        if(serviceOfferings==null){
            throw new Exception("Salon Id not found");
        }
        if(categoryId!=null){
           serviceOfferings = (Set<ServiceOffering>) serviceOfferings.stream().filter(serviceOffering -> serviceOffering.getCategoryId().equals(categoryId)).toList();

        }
        return serviceOfferings;

    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) throws Exception {
        List<ServiceOffering> serviceOfferings = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(serviceOfferings);
    }
    public ServiceOffering getServiceById(Long serviceId) throws Exception {
        return serviceOfferingRepository.findById(serviceId).orElse(null);
    }
}
