package br.com.lbenaducci.fcddd.domain.customer.repository;

import br.com.lbenaducci.fcddd.domain.customer.entity.Customer;
import br.com.lbenaducci.fcddd.domain.shared.repository.Repository;

import java.util.UUID;

public interface CustomerRepository extends Repository<Customer, UUID> {
}
