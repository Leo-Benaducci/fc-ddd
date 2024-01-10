package br.com.lbenaducci.fcddd.infrastructure.customer.jpa.repository;

import br.com.lbenaducci.fcddd.domain.customer.entity.Customer;
import br.com.lbenaducci.fcddd.domain.customer.record.Address;
import br.com.lbenaducci.fcddd.infrastructure.customer.jpa.model.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryImplTest {
	@Autowired
	private CustomerRepositoryJpa jpaRepository;
	@Autowired
	private CustomerRepositoryImpl repository;

	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "John";
	private static final Address ADDRESS = new Address("street", "number", "zipCode", "city");
	private static final CustomerModel MODEL = createCustomerModel(ID, NAME, ADDRESS, true, 10);

	@Test
	void shouldCreateCustomer() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		customer.addRewardPoints(10);
		repository.create(customer);

		CustomerModel found = jpaRepository.findById(ID).orElse(null);
		assertNotNull(found);

		assertEquals(ID, found.getId());
		assertEquals(NAME, found.getName());
		assertEquals(ADDRESS.street(), found.getStreet());
		assertEquals(ADDRESS.number(), found.getNumber());
		assertEquals(ADDRESS.zipCode(), found.getZipCode());
		assertEquals(ADDRESS.city(), found.getCity());
		assertTrue(found.isActive());
		assertEquals(10, found.getRewardPoints());
	}

	@Test
	void shouldUpdateCustomer() {
		jpaRepository.save(MODEL);

		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		customer.addRewardPoints(10);

		customer.changeName("Jane");
		repository.update(customer);

		CustomerModel found = jpaRepository.findById(ID).orElse(null);
		assertNotNull(found);
		assertEquals(ID, found.getId());
		assertEquals("Jane", found.getName());
	}

	@Test
	void shouldDeleteCustomer() {
		jpaRepository.save(MODEL);

		Customer customer = new Customer(ID, NAME);

		repository.delete(customer);
		CustomerModel found = jpaRepository.findById(ID).orElse(null);
		assertNull(found);
	}

	@Test
	void shouldFindCustomerById() {
		jpaRepository.save(MODEL);

		Customer found = repository.findById(ID);
		assertEquals(ID, found.id());
		assertEquals(NAME, found.name());
		assertEquals(ADDRESS, found.address());
		assertTrue(found.isActive());
		assertEquals(10, found.rewardPoints());

		UUID id = UUID.randomUUID();
		CustomerModel model2 = createCustomerModel(id, NAME, ADDRESS, false, 10);
		jpaRepository.save(model2);

		Customer found2 = repository.findById(id);
		assertEquals(id, found2.id());
		assertEquals(NAME, found2.name());
		assertEquals(ADDRESS, found2.address());
		assertFalse(found2.isActive());
		assertEquals(10, found2.rewardPoints());

		id = UUID.randomUUID();
		CustomerModel model3 = createCustomerModel(id, NAME, ADDRESS, true, 0);
		jpaRepository.save(model3);

		Customer found3 = repository.findById(id);
		assertEquals(id, found3.id());
		assertEquals(NAME, found3.name());
		assertEquals(ADDRESS, found3.address());
		assertTrue(found3.isActive());
		assertEquals(0, found3.rewardPoints());

		id = UUID.randomUUID();
		CustomerModel model4 = createCustomerModel(id, NAME, ADDRESS, false, 0);
		jpaRepository.save(model4);

		Customer found4 = repository.findById(id);
		assertEquals(id, found4.id());
		assertEquals(NAME, found4.name());
		assertEquals(ADDRESS, found4.address());
		assertFalse(found4.isActive());
		assertEquals(0, found4.rewardPoints());

		id = UUID.randomUUID();
		CustomerModel model5 = createCustomerModel(id, NAME, null, false, 0);
		jpaRepository.save(model5);

		Customer found5 = repository.findById(id);
		assertEquals(id, found5.id());
		assertEquals(NAME, found5.name());
		assertNull(found5.address());
		assertFalse(found5.isActive());
		assertEquals(0, found5.rewardPoints());
	}

	@Test
	void shouldFindAllCustomers() {
		jpaRepository.save(MODEL);
		UUID id = UUID.randomUUID();
		CustomerModel model2 = createCustomerModel(id, "Jane", ADDRESS, false, 5);
		jpaRepository.save(model2);

		List<Customer> found = repository.findAll(0, 10);
		assertEquals(2, found.size());
		assertEquals(ID, found.get(0).id());
		assertEquals(NAME, found.get(0).name());
		assertEquals(id, found.get(1).id());
		assertEquals("Jane", found.get(1).name());
	}

	private static CustomerModel createCustomerModel(UUID id, String name, Address address, boolean active, int rewardPoints) {
		CustomerModel model = new CustomerModel();
		model.setId(id);
		model.setName(name);
		model.setActive(active);
		model.setRewardPoints(rewardPoints);
		if(address != null) {
			model.setStreet(address.street());
			model.setNumber(address.number());
			model.setZipCode(address.zipCode());
			model.setCity(address.city());
		}
		return model;
	}
}