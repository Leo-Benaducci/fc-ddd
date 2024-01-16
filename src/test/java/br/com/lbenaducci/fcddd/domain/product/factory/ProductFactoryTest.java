package br.com.lbenaducci.fcddd.domain.product.factory;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

	@Test
	void shouldThrowExceptionWhenConstructFactory() {
		assertThrows(UnsupportedOperationException.class, ProductFactory::new);
	}

	@Test
	void shouldCreateProduct() {
		Product product = ProductFactory.create("name", BigDecimal.ONE);
		assertNotNull(product);
		assertNotNull(product.id());
		assertEquals("name", product.name());
		assertEquals(BigDecimal.ONE, product.price());
	}

	@Test
	void shouldInstanceProduct() {
		UUID id = UUID.randomUUID();
		Product product = ProductFactory.instance(id, "name", BigDecimal.ONE);
		assertNotNull(product);
		assertEquals(id, product.id());
		assertEquals("name", product.name());
		assertEquals(BigDecimal.ONE, product.price());
	}

}