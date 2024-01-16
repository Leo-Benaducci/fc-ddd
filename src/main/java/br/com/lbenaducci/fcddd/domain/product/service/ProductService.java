package br.com.lbenaducci.fcddd.domain.product.service;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProductService {
	ProductService() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static void increasePrice(List<Product> products, BigDecimal percentage) {
		if(percentage == null || percentage.doubleValue() <= 0) {
			throw new IllegalArgumentException("Percentage cannot be null or zero or negative");
		}
		BigDecimal multiplayer = percentage.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
		products.forEach(p -> p.changePrice(p.price().multiply(multiplayer)));
	}
}
