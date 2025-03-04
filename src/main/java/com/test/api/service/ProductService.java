package com.test.api.service;

import com.test.api.model.*;
import com.test.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String initializeData() {
        List<Product> products = Arrays.asList(
                createProduct("0d835b0d-4d4e-46cc-88e1-5169f638f6dc", "Paint",
                        List.of(createCategory(1, "Coverings"), createCategory(3, "End product")),
                        List.of("new-product")),
                createProduct("57f318aa-75ee-4b4b-a7c0-e17400b91897", "Mortar",
                        List.of(createCategory(2, "Building products"), createCategory(3, "End product")),
                        null),
                createProduct("75ee-4baa-0d835bb-a7c0-e174005169f7", "Expanded cork",
                        List.of(createCategory(4, "Insulation"), createCategory(2, "Building products")),
                        List.of("bio-based"))
        );
        productRepository.saveAll(products);
        return "Data initialized";
    }

    private Product createProduct(String uuid, String name, List<Product.Category> categories, List<String> tags) {
        Product product = new Product();
        product.setUuid(uuid);
        product.setName(name);
        product.setCategories(categories);
        product.setTags(tags);
        return product;
    }

    private Product.Category createCategory(int id, String name) {
        Product.Category category = new Product.Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    public List<Product> showAll() {
        return productRepository.findAll();
    }

    public List<Product> searchByCategory(String categoryName) {
        return productRepository.findByCategoriesName(categoryName);
    }

    public List<Product> searchByTag(String tagName) {
        return productRepository.findByTags(tagName);
    }
}

