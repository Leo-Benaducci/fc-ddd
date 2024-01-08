package br.com.lbenaducci.fcddd.infrastructure.customer.jpa.repository;

import br.com.lbenaducci.fcddd.domain.customer.entity.Customer;
import br.com.lbenaducci.fcddd.domain.customer.record.Address;
import br.com.lbenaducci.fcddd.domain.customer.repository.CustomerRepository;
import br.com.lbenaducci.fcddd.infrastructure.customer.jpa.model.CustomerModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	private final CustomerRepositoryJpa jpaRepository;

	@Autowired
	public CustomerRepositoryImpl(CustomerRepositoryJpa jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public void create(Customer entity) {
		jpaRepository.save(toModel(entity));
	}

	@Override
	public void update(Customer entity) {
		jpaRepository.save(toModel(entity));
	}

	@Override
	public void delete(Customer entity) {
		jpaRepository.deleteById(entity.id());
	}

	@Override
	public Customer findById(UUID id) {
		CustomerModel model = jpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " not found"));
		return fromModel(model);
	}

	@Override
	public List<Customer> findAll(int page, int size) {
		return jpaRepository.findAll(PageRequest.of(page, size)).map(this::fromModel).getContent();
	}

	private CustomerModel toModel(Customer customer) {
		CustomerModel model = new CustomerModel();
		model.setId(customer.id());
		model.setName(customer.name());
		model.setActive(customer.isActive());
		model.setRewardPoints(customer.rewardPoints());
		model.setStreet(customer.address().street());
		model.setNumber(customer.address().number());
		model.setZipCode(customer.address().zipCode());
		model.setCity(customer.address().city());
		return model;
	}

	private Customer fromModel(CustomerModel model) {
		Customer customer = new Customer(model.getId(), model.getName());
		Address address = new Address(model.getStreet(), model.getNumber(), model.getZipCode(), model.getCity());
		customer.changeAddress(address);
		if(model.isActive() || model.getRewardPoints() > 0) {
			customer.activate();
		}
		if(model.getRewardPoints() > 0) {
			customer.addRewardPoints(model.getRewardPoints());
			if(!model.isActive()) {
				customer.deactivate();
			}
		}
		return customer;
	}
}
