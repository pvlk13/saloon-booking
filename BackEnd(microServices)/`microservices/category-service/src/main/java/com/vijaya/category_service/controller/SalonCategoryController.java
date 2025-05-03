package com.vijaya.category_service.controller;


import com.vijaya.category_service.modal.Category;
import com.vijaya.category_service.payload.dto.SalonDTO;
import com.vijaya.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        return ResponseEntity.ok(categoryService.createCategory(category, salonDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        if (categoryService.getCategoryById(id) != null) {
            categoryService.deleteCategory(id, salonDTO.getId());
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
