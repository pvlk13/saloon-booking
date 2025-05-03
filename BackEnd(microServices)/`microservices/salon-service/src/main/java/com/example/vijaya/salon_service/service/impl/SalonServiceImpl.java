package com.example.vijaya.salon_service.service.impl;

import com.example.vijaya.salon_service.modal.Salon;
import com.example.vijaya.salon_service.payload.dto.SalonDTO;
import com.example.vijaya.salon_service.payload.dto.UserDTO;
import com.example.vijaya.salon_service.repository.SalonRepository;
import com.example.vijaya.salon_service.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceImpl implements SalonService {
    @Autowired
    private SalonRepository salonRepository;
    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {
       Salon salon = new Salon();

       salon.setState(salonDTO.getState());
       salon.setCity(salonDTO.getCity());
       salon.setAddress(salonDTO.getAddress());
       salon.setName(salonDTO.getName());
       salon.setContactNumber(salonDTO.getContactNumber());
       salon.setClosingTime(salonDTO.getClosingTime());
       salon.setOpeningTime(salonDTO.getOpeningTime());
       salon.setImages(salonDTO.getImages());
       salon.setOwnerId(1L);
       return salonRepository.save(salon);
    }
    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long salonId) {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        boolean user = salonDTO.getOwnerId().equals(userDTO.getId());
        if(salon != null){
         salon.setState(salonDTO.getState());
            salon.setCity(salonDTO.getCity());
            salon.setAddress(salonDTO.getAddress());
            salon.setName(salonDTO.getName());
            salon.setContactNumber(salonDTO.getContactNumber());
            salon.setClosingTime(salonDTO.getClosingTime());
            salon.setOpeningTime(salonDTO.getOpeningTime());
            salon.setImages(salonDTO.getImages());
            salon.setOwnerId(1L);
            return salonRepository.save(salon);
        }
        throw new RuntimeException("salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
      return   salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
       Optional<Salon> salon = salonRepository.findById(salonId);
       if(salon.isPresent()){
           return salon.get();
       }
       throw new RuntimeException("salon not exist");
    }

    @Override
    public Salon getSalonByOwnerID(Long ownerId) {
        Optional<Salon> salonOwnerId = Optional.ofNullable(salonRepository.findByOwnerId(ownerId));
        if(salonOwnerId.isPresent()){
            return salonOwnerId.get();
        }
        throw new RuntimeException("salon can't be found for this ownerId");
    }

    @Override
    public List<Salon> getSalonByCity(String city) {
        Optional<List<Salon>> salonByCity = Optional.ofNullable(salonRepository.searchSalons(city));
        if(salonByCity.isPresent()){
            return salonByCity.get();
        }
        throw new RuntimeException("salon can't be found for this city");
    }
}
