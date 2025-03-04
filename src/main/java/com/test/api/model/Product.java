
package com.test.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Product {

    @Id
    private String id;
    private String uuid;
    private String name;
    private List<Category> categories;
    private List<String> tags;
}
}
