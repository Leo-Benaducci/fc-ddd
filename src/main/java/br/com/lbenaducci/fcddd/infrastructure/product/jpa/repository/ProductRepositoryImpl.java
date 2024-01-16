package br.com.lbenaducci.fcddd.infrastructure.product.jpa.repository;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;
import br.com.lbenaducci.fcddd.domain.product.factory.ProductFactory;
import br.com.lbenaducci.fcddd.domain.product.repository.ProductRepository;
import br.com.lbenaducci.fcddd.infrastructure.product.jpa.model.ProductModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private final ProductRepositoryJpa jpaRepository;

	@Autowired
	public ProductRepositoryImpl(ProductRepositoryJpa jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public void create(Product entity) {
		jpaRepository.save(toModel(entity));
	}

	@Override
	public void update(Product entity) {
		jpaRepository.save(toModel(entity));
	}

	@Override
	public void delete(Product entity) {
		jpaRepository.deleteById(entity.id());
	}

	@Override
	public Product findById(UUID id) {
		ProductModel model = jpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product " + id + " not found"));
		return fromModel(model);
	}

	@Override
	public List<Product> findAll(int page, int size) {
		return jpaRepository.findAll(PageRequest.of(page, size)).map(this::fromModel).getContent();
	}

	private ProductModel toModel(Product product) {
		ProductModel model = new ProductModel();
		model.setId(product.id());
		model.setName(product.name());
		model.setPrice(product.price());
		return model;
	}

	private Product fromModel(ProductModel model) {
		return ProductFactory.instance(model.getId(), model.getName(), model.getPrice());
	}
}
