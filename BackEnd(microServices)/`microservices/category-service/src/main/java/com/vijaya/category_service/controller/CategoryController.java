package com.vijaya.category_service.controller;

import com.vijaya.category_service.modal.Category;
import com.vijaya.category_service.payload.dto.SalonDTO;
import com.vijaya.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>> getAllCategoriesBySalonId(@PathVariable Long salonId) {
        return ResponseEntity.ok(categoryService.getAllCategoriesBySalonId(salonId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}