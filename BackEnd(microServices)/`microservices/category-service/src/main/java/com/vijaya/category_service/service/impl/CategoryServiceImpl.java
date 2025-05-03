package com.vijaya.category_service.service.impl;

import com.vijaya.category_service.modal.Category;
import com.vijaya.category_service.payload.dto.SalonDTO;
import com.vijaya.category_service.repository.CategoryRepository;
import com.vijaya.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category, SalonDTO salonDTO) {
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setSalonId(salonDTO.getId());
        category1.setImages(category.getImages());
        return categoryRepository.save(category1);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        Category category = categoryRepository.findAllBySalonId(salonId).stream().findFirst().orElse(null);
        if(category == null){
            throw new RuntimeException("Salon Id not found");
        }
        return categoryRepository.findAllBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long id) {
       Category category = categoryRepository.findById(id).orElse(null);
       if(category == null){
           throw new RuntimeException("Category not found");
       }
       return category;
    }

    @Override
    public void deleteCategory(Long id, Long salonId) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null){
            throw new RuntimeException("Category not found");
        }
        if(!category.getSalonId().equals(salonId)){
            throw new RuntimeException("Don't have permission to delete this category");
        }
        categoryRepository.deleteById(id);
    }

}
