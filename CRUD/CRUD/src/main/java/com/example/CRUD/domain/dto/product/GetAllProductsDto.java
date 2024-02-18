package com.example.CRUD.domain.dto.product;

import com.example.CRUD.domain.model.product.ProductModel;

public record GetAllProductsDto(String id, String name, Integer price_in_cents) {

	public GetAllProductsDto(ProductModel productModel) {
		this(productModel.getId(), productModel.getName(), productModel.getPrice_in_cents());
	}
}
