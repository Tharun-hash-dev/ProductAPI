package com.example.documentapi.service;

import com.example.documentapi.model.Product;
import com.example.documentapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private List<Product> testProducts;

    @BeforeEach
    void setUp() {
        testProducts = Arrays.asList(
                createProduct("0d835b0d-4d4e-46cc-88e1-5169f638f6dc", "Paint",
                        createCategories(1, "Coverings", 3, "End product"),
                        List.of("new-product")),
                createProduct("57f318aa-75ee-4b4b-a7c0-e17400b91897", "Mortar",
                        createCategories(2, "Building products", 3, "End product"),
                        null),
                createProduct("75ee-4baa-0d835bb-a7c0-e174005169f7", "Expanded cork",
                        createCategories(4, "Insulation", 2, "Building products"),
                        List.of("bio-based"))
        );
    }

    private Product createProduct(String uuid, String name, List<Product.Category> categories, List<String> tags) {
        Product product = new Product();
        product.setUuid(uuid);
        product.setName(name);
        product.setCategories(categories);
        product.setTags(tags);
        return product;
    }

    private List<Product.Category> createCategories(int id1, String name1, int id2, String name2) {
        return Arrays.asList(createCategory(id1, name1), createCategory(id2, name2));
    }

    private Product.Category createCategory(int id, String name) {
        Product.Category category = new Product.Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    @Test
    void testInitializeData() {
        when(productRepository.saveAll(anyList())).thenReturn(testProducts);
        String result = productService.initializeData();
        assertEquals("Data initialized", result);
        verify(productRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testShowAll() {
        when(productRepository.findAll()).thenReturn(testProducts);
        List<Product> result = productService.showAll();
        assertEquals(testProducts, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSearchByCategory() {
        String categoryName = "Coverings";
        List<Product> expectedResult = List.of(testProducts.get(0));
        when(productRepository.findByCategoriesName(categoryName)).thenReturn(expectedResult);
        List<Product> result = productService.searchByCategory(categoryName);
        assertEquals(expectedResult, result);
        verify(productRepository, times(1)).findByCategoriesName(categoryName);
    }

    @Test
    void testSearchByTag() {
        String tagName = "bio-based";
        List<Product> expectedResult = List.of(testProducts.get(2));
        when(productRepository.findByTags(tagName)).thenReturn(expectedResult);
        List<Product> result = productService.searchByTag(tagName);
        assertEquals(expectedResult, result);
        verify(productRepository, times(1)).findByTags(tagName);
    }
}
