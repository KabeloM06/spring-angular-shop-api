package com.kabelo.springangularshop.dao;

import com.kabelo.springangularshop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
