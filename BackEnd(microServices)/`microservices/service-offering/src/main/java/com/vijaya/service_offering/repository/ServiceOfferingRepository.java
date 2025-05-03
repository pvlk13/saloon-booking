package com.vijaya.service_offering.repository;

import com.vijaya.service_offering.modal.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {
  Set<ServiceOffering> findAllBySalonId(Long salonId);
}
