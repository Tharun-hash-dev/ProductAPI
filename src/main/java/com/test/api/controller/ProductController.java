package com.test.api.controller;

import com.test.api.model.*;
import com.test.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/init")
    public String initializeData() {
        return productService.initializeData();
    }

    @GetMapping("/show_all")
    public List<Product> showAll() {
        return productService.showAll();
    }

    @GetMapping("/categories/{cat_name}")
    public List<Product> searchByCategory(@PathVariable String cat_name) {
        return productService.searchByCategory(cat_name);
    }

    @GetMapping("/tags/{tagname}")
    public List<Product> searchByTag(@PathVariable String tagname) {
        return productService.searchByTag(tagname);
    }
}

