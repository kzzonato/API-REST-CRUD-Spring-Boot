package com.example.CRUD.domain.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUD.domain.model.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, String>{

	List<ProductModel> findAllByActiveTrue();
}
