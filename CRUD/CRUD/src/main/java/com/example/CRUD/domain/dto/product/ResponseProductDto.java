package com.example.CRUD.domain.dto.product;

import com.example.CRUD.domain.model.product.ProductModel;

public record ResponseProductDto(String name, Integer price_in_cents) {

	public ResponseProductDto(ProductModel product) {
		this(product.getName(), product.getPrice_in_cents());
	}
}
