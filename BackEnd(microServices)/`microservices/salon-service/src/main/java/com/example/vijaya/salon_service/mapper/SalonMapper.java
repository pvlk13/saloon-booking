package com.example.vijaya.salon_service.mapper;

import com.example.vijaya.salon_service.modal.Salon;
import com.example.vijaya.salon_service.payload.dto.SalonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalonMapper {
    @Autowired
    private SalonDTO salonDTO;
    public SalonDTO mapToDTO(Salon salon){
        salonDTO.setClosingTime(salon.getClosingTime());
        salonDTO.setImages(salon.getImages());
        salonDTO.setOpeningTime(salon.getOpeningTime());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setCity(salon.getCity());
        salonDTO.setName(salon.getName());
        salonDTO.setContactNumber(salon.getContactNumber());
        salonDTO.setState(salon.getState());
       // salonDTO.setOwnerId(salon.getOwnerId());
        return salonDTO;

    }
}
