
package com.test.api.repository;

import com.test.api.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    
    List<Product> findByCategoriesName(String categoryName);
    List<Product> findByTags(String tagName);
}
