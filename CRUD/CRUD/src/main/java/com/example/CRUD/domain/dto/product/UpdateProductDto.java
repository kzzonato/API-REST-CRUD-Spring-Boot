package com.example.CRUD.domain.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductDto(@NotBlank String id,@NotBlank String name,@NotNull Integer price_in_cents) {
	
}
