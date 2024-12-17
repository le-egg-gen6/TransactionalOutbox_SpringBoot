package org.myproject.productservice.repository;

import org.myproject.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 10:20 AM Tue 12/17/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
