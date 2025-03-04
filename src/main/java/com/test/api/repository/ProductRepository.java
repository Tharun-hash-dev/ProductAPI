
package com.test.api.repository;

import com.test.api.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'categories.name' : ?0 }")
    List<Product> findByCategoriesName(String categoryName);

    @Query("{ 'tags' : ?0 }")
    List<Product> findByTags(String tagName);
}
