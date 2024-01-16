package br.com.lbenaducci.fcddd.domain.product.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "product";
	private static final BigDecimal PRICE = BigDecimal.TEN;

	@Test
	void shouldThrowExceptionWhenIdIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Product(null, NAME, PRICE));
	}

	@Test
	void shouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Product(ID, null, PRICE));
	}

	@Test
	void shouldThrowExceptionWhenNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Product(ID, "", PRICE));
	}

	@Test
	void shouldThrowExceptionWhenNameIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Product(ID, " ", PRICE));
	}

	@Test
	void shouldThrowExceptionWhenPriceIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Product(ID, NAME, null));
	}

	@Test
	void shouldThrowExceptionWhenPriceIsNegative() {
		BigDecimal price = BigDecimal.valueOf(-1);
		assertThrows(IllegalArgumentException.class, () -> new Product(ID, NAME, price));
	}

	@Test
	void shouldChangeName() {
		Product product = new Product(ID, NAME, PRICE);
		product.changeName("newName");
		assertEquals("newName", product.name());
	}

	@Test
	void shouldThrowExceptionWhenNameIsBlankWhenChangeName() {
		Product product = new Product(ID, NAME, PRICE);
		assertThrows(IllegalArgumentException.class, () -> product.changeName(" "));
		assertEquals(NAME, product.name());
	}

	@Test
	void shouldChangePrice() {
		Product product = new Product(ID, NAME, PRICE);
		product.changePrice(BigDecimal.valueOf(20));
		assertEquals(BigDecimal.valueOf(20), product.price());
	}

	@Test
	void shouldThrowExceptionWhenPriceIsNegativeWhenChangePrice() {
		Product product = new Product(ID, NAME, PRICE);
		BigDecimal price = BigDecimal.valueOf(-1);
		assertThrows(IllegalArgumentException.class, () -> product.changePrice(price));
		assertEquals(PRICE, product.price());
	}
}