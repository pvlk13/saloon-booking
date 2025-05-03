package com.example.vijaya.salon_service.controller;
import com.example.vijaya.salon_service.mapper.SalonMapper;
import com.example.vijaya.salon_service.modal.Salon;
import com.example.vijaya.salon_service.payload.dto.SalonDTO;
import com.example.vijaya.salon_service.payload.dto.UserDTO;
import com.example.vijaya.salon_service.repository.SalonRepository;
import com.example.vijaya.salon_service.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {
    @Autowired
    private SalonService salonService;
    @Autowired
    private SalonMapper salonMapper;
    @Autowired
    private SalonRepository salonRepository;
    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO,userDTO);
        SalonDTO salonDTO1 = salonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }
    @PutMapping(value = "/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@RequestBody SalonDTO salonDTO,
                                                @PathVariable Long salonId,
                                                UserDTO userDTO){
        Salon salon = salonService.updateSalon(salonDTO,userDTO,salonId);
        SalonDTO salonDTO1 = salonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);

    }
    @GetMapping
   public ResponseEntity<List<SalonDTO>> getAllSalons(){
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOS = salons.stream().map(
                salon -> salonMapper.mapToDTO(salon)
        ).toList();
      return ResponseEntity.ok(salonDTOS);
   }
   @GetMapping("/{salonId}")
   public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId){
        Salon salon = salonService.getSalonById(salonId);
        SalonDTO salonDTO = salonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
   }
   @GetMapping("/owner/{ownerId}")
   public ResponseEntity<SalonDTO> getSalonByOwnerID(@PathVariable Long ownerId){
        Salon salon = salonService.getSalonByOwnerID(ownerId);
        SalonDTO salonDTO = salonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
   }
   @GetMapping("/cities/{city}")
   public ResponseEntity<List<SalonDTO>> getSalonByCity(@PathVariable String city){
       List<Salon> salons = salonService.getSalonByCity(city);
       List<SalonDTO> salonDTOS = salons.stream().map(salon -> salonMapper.mapToDTO(salon)).toList();
       return ResponseEntity.ok(salonDTOS);
   }
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Salon> searchedSalons = salonRepository.searchSalons(keyword);
        List<SalonDTO> salonDTOS = searchedSalons.stream()
                .map(salonMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(salonDTOS);
    }


}
