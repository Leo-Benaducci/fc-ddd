package br.com.lbenaducci.fcddd.infrastructure.customer.jpa.repository;

import br.com.lbenaducci.fcddd.infrastructure.customer.jpa.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepositoryJpa extends JpaRepository<CustomerModel, UUID> {
}
