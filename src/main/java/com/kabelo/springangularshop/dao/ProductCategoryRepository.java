package com.kabelo.springangularshop.dao;

import com.kabelo.springangularshop.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")//name of JSON entry and path will be '/product-category'
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
