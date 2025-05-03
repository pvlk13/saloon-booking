package com.vijaya.category_service.service;

import com.vijaya.category_service.modal.Category;
import com.vijaya.category_service.payload.dto.SalonDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {
     Category createCategory(Category category, SalonDTO salonDTO);
     Set<Category> getAllCategoriesBySalonId(Long salonId);
     Category getCategoryById(Long id);
     void deleteCategory(Long id, Long salonId);
}
