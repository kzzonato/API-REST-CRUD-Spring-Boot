package com.example.CRUD.domain.model.product;

import com.example.CRUD.domain.dto.product.RequestProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity(name="product")
@EqualsAndHashCode(of="id")
public class ProductModel {

	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private Integer price_in_cents;
	private Boolean active;
	
	public ProductModel(RequestProductDto data) {
		this.name = data.name();
		this.price_in_cents = data.price_in_cents();
		this.active = true;
	}	
}
