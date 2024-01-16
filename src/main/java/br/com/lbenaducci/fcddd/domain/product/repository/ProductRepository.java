package br.com.lbenaducci.fcddd.domain.product.repository;

import br.com.lbenaducci.fcddd.domain.product.entity.Product;
import br.com.lbenaducci.fcddd.domain.shared.repository.Repository;

import java.util.UUID;

public interface ProductRepository extends Repository<Product, UUID> {
}
