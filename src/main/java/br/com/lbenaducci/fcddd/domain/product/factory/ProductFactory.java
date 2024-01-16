package br.com.lbenaducci.fcddd.domain.product.factory;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductFactory {
	ProductFactory() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static Product create(String name, BigDecimal price) {
		return new Product(UUID.randomUUID(), name, price);
	}

	public static Product instance(UUID id, String name, BigDecimal price) {
		return new Product(id, name, price);
	}
}
