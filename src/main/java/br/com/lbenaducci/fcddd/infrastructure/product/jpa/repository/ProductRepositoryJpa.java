package br.com.lbenaducci.fcddd.infrastructure.product.jpa.repository;

import br.com.lbenaducci.fcddd.infrastructure.product.jpa.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductModel, UUID> {
}
