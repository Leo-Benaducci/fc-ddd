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
	private static final CustomerModel MODEL = createCustomerModel(ID, NAME, true, 10);

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
		assertEquals(ADDRESS.street(), found.address().street());
		assertEquals(ADDRESS.number(), found.address().number());
		assertEquals(ADDRESS.zipCode(), found.address().zipCode());
		assertEquals(ADDRESS.city(), found.address().city());
		assertTrue(found.isActive());
		assertEquals(10, found.rewardPoints());

		UUID id = UUID.randomUUID();
		CustomerModel model2 = createCustomerModel(id, NAME, false, 10);
		jpaRepository.save(model2);

		Customer found2 = repository.findById(id);
		assertEquals(id, found2.id());
		assertEquals(NAME, found2.name());
		assertEquals(ADDRESS.street(), found2.address().street());
		assertEquals(ADDRESS.number(), found2.address().number());
		assertEquals(ADDRESS.zipCode(), found2.address().zipCode());
		assertEquals(ADDRESS.city(), found2.address().city());
		assertFalse(found2.isActive());
		assertEquals(10, found2.rewardPoints());
	}

	@Test
	void shouldFindAllCustomers() {
		jpaRepository.save(MODEL);
		UUID id = UUID.randomUUID();
		CustomerModel model2 = createCustomerModel(id, "Jane", false, 5);
		jpaRepository.save(model2);

		List<Customer> found = repository.findAll(0, 10);
		assertEquals(2, found.size());
		assertEquals(ID, found.get(0).id());
		assertEquals(NAME, found.get(0).name());
		assertEquals(id, found.get(1).id());
		assertEquals("Jane", found.get(1).name());
	}

	private static CustomerModel createCustomerModel(UUID id, String name, boolean active, int rewardPoints) {
		CustomerModel model = new CustomerModel();
		model.setId(id);
		model.setName(name);
		model.setActive(active);
		model.setRewardPoints(rewardPoints);
		model.setStreet(ADDRESS.street());
		model.setNumber(ADDRESS.number());
		model.setZipCode(ADDRESS.zipCode());
		model.setCity(ADDRESS.city());
		return model;
	}
}