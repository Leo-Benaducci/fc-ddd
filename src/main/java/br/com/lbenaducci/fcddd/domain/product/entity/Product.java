package br.com.lbenaducci.fcddd.domain.product.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
	private final UUID id;
	private String name;
	private BigDecimal price;

	public Product(UUID id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
		validate();
	}

	public UUID id() {
		return id;
	}

	public String name() {
		return name;
	}

	public BigDecimal price() {
		return price;
	}

	private void validate() {
		if(id == null) {
			throw new IllegalArgumentException("Product id cannot be null");
		}
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Product name cannot be null or blank");
		}
		if(price == null || price.doubleValue() < 0) {
			throw new IllegalArgumentException("Product price cannot be null or negative");
		}
	}

	public void changeName(String name) {
		String oldName = this.name;
		try {
			this.name = name;
			validate();
		} catch(IllegalArgumentException e) {
			this.name = oldName;
			throw e;
		}
	}

	public void changePrice(BigDecimal price) {
		BigDecimal oldPrice = this.price;
		try {
			this.price = price;
			validate();
		} catch(IllegalArgumentException e) {
			this.price = oldPrice;
			throw e;
		}
	}
}
