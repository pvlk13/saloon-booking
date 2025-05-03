package com.example.vijaya.salon_service.service;

import com.example.vijaya.salon_service.modal.Salon;
import com.example.vijaya.salon_service.payload.dto.SalonDTO;
import com.example.vijaya.salon_service.payload.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SalonService {
    Salon createSalon(SalonDTO salonDTO, UserDTO userDTO);
    Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long salonId);
    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);
    Salon getSalonByOwnerID(Long ownerId);
    List<Salon> getSalonByCity(String city);
}
