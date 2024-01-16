package br.com.lbenaducci.fcddd.infrastructure.product.jpa.repository;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;
import br.com.lbenaducci.fcddd.infrastructure.product.jpa.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryImplTest {
	@Autowired
	private ProductRepositoryJpa jpaRepository;
	@Autowired
	private ProductRepositoryImpl repository;

	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "Product";
	private static final BigDecimal PRICE = BigDecimal.TEN;
	private static final ProductModel MODEL = createProductModel(ID, NAME, PRICE);

	@Test
	void shouldCreateProduct() {
		Product product = new Product(ID, NAME, PRICE);
		repository.create(product);

		ProductModel found = jpaRepository.findById(ID).orElse(null);
		assertNotNull(found);

		assertEquals(ID, found.getId());
		assertEquals(NAME, found.getName());
		assertEquals(PRICE.doubleValue(), found.getPrice().doubleValue());
	}

	@Test
	void shouldUpdateProduct() {
		jpaRepository.save(MODEL);

		Product product = new Product(ID, NAME, PRICE);
		product.changePrice(BigDecimal.ONE);
		repository.update(product);

		ProductModel found = jpaRepository.findById(ID).orElse(null);
		assertNotNull(found);

		assertEquals(ID, found.getId());
		assertEquals(NAME, found.getName());
		assertEquals(BigDecimal.ONE.doubleValue(), found.getPrice().doubleValue());
	}

	@Test
	void shouldDeleteProduct() {
		jpaRepository.save(MODEL);

		Product product = new Product(ID, NAME, PRICE);
		repository.delete(product);

		ProductModel found = jpaRepository.findById(ID).orElse(null);
		assertNull(found);
	}

	@Test
	void shouldFindProduct() {
		jpaRepository.save(MODEL);

		Product found = repository.findById(ID);
		assertEquals(ID, found.id());
		assertEquals(NAME, found.name());
		assertEquals(PRICE.doubleValue(), found.price().doubleValue());
	}

	@Test
	void shouldFindAllProducts() {
		jpaRepository.save(MODEL);
		UUID id = UUID.randomUUID();
		jpaRepository.save(createProductModel(id, "Product2", BigDecimal.ONE));

		List<Product> found = repository.findAll(0, 10);
		assertEquals(2, found.size());
		assertEquals(ID, found.get(0).id());
		assertEquals(NAME, found.get(0).name());
		assertEquals(PRICE.doubleValue(), found.get(0).price().doubleValue());
		assertEquals(id, found.get(1).id());
		assertEquals("Product2", found.get(1).name());
		assertEquals(BigDecimal.ONE.doubleValue(), found.get(1).price().doubleValue());
	}

	private static ProductModel createProductModel(UUID id, String name, BigDecimal price) {
		ProductModel model = new ProductModel();
		model.setId(id);
		model.setName(name);
		model.setPrice(price);
		return model;
	}
}