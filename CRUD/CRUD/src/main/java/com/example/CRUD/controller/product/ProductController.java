package com.example.CRUD.controller.product;

import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUD.domain.dto.product.GetAllProductsDto;
import com.example.CRUD.domain.dto.product.RequestProductDto;
import com.example.CRUD.domain.dto.product.ResponseProductDto;
import com.example.CRUD.domain.dto.product.UpdateProductDto;
import com.example.CRUD.domain.model.product.ProductModel;
import com.example.CRUD.domain.repository.product.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<GetAllProductsDto>> getAllProducts() {
		var allProducts = productRepository.findAllByActiveTrue().stream().map(GetAllProductsDto::new).toList();
		return ResponseEntity.ok(allProducts);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity getProductById(@PathVariable String id) {
		Optional<ProductModel> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			var product = optionalProduct.get();
			return ResponseEntity.ok(new ResponseProductDto(product));
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity registerProduct(@RequestBody @Valid RequestProductDto data) {
		var product = new ProductModel(data);
		productRepository.save(product);	
		return ResponseEntity.ok(new ResponseProductDto(product));
	}
	
	
	@PutMapping()
	@Transactional
	public ResponseEntity updateProduct(@RequestBody @Valid  UpdateProductDto data) {
		Optional<ProductModel> optionalProduct = productRepository.findById(data.id());
		if(optionalProduct.isPresent()) {
			var product = optionalProduct.get();
			product.setName(data.name());
			product.setPrice_in_cents(data.price_in_cents());
			return ResponseEntity.ok(new ResponseProductDto(product));
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteProduct(@PathVariable String id) {
		Optional<ProductModel> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			var product = optionalProduct.get();
			product.setActive(false);
			return ResponseEntity.noContent().build();
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity activateProduct(@PathVariable String id) {
		Optional<ProductModel> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			var product = optionalProduct.get();
			product.setActive(true);
			return ResponseEntity.noContent().build();
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	
 }
