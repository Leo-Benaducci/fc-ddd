package br.com.lbenaducci.fcddd.domain.product.service;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductServiceTest {
	@Test
	void shouldThrowExceptionWhenInstanceProductService() {
		assertThrows(UnsupportedOperationException.class, ProductService::new);
	}

	@Test
	void shouldIncreasePrice() {
		Product product = new Product(UUID.randomUUID(), "Product", BigDecimal.TEN);
		Product product2 = new Product(UUID.randomUUID(), "Product", BigDecimal.valueOf(20));
		List<Product> products = List.of(product, product2);
		BigDecimal percentage = BigDecimal.valueOf(100);
		ProductService.increasePrice(products, percentage);

		assertEquals(BigDecimal.valueOf(20).doubleValue(), product.price().doubleValue());
		assertEquals(BigDecimal.valueOf(40).doubleValue(), product2.price().doubleValue());
	}

	@Test
	void shouldThrowExceptionWhenPercentageIsZeroOrNegative() {
		Product product = new Product(UUID.randomUUID(), "Product", BigDecimal.TEN);
		Product product2 = new Product(UUID.randomUUID(), "Product", BigDecimal.valueOf(20));
		List<Product> products = List.of(product, product2);
		BigDecimal percentage = BigDecimal.valueOf(0);
		assertThrows(IllegalArgumentException.class, () -> ProductService.increasePrice(products, percentage));
		BigDecimal percentage2 = BigDecimal.valueOf(-1);
		assertThrows(IllegalArgumentException.class, () -> ProductService.increasePrice(products, percentage2));
	}

	@Test
	void shouldThrowExceptionWhenPercentageIsNull() {
		Product product = new Product(UUID.randomUUID(), "Product", BigDecimal.TEN);
		Product product2 = new Product(UUID.randomUUID(), "Product", BigDecimal.valueOf(20));
		List<Product> products = List.of(product, product2);
		assertThrows(IllegalArgumentException.class, () -> ProductService.increasePrice(products, null));
	}
}