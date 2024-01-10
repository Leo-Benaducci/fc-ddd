package br.com.lbenaducci.fcddd.domain.customer.factory;

import br.com.lbenaducci.fcddd.domain.customer.entity.Customer;
import br.com.lbenaducci.fcddd.domain.customer.datatype.Address;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {
	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "John";
	private static final Address ADDRESS = new Address("street", "number", "zipCode", "city");

	@Test
	void shouldThrowExceptionWhenConstructFactory() {
		assertThrows(UnsupportedOperationException.class, CustomerFactory::new);
	}

	@Test
	void shouldCreateCustomer() {
		Customer customer = CustomerFactory.create(NAME);
		assertNotNull(customer);
		assertNotNull(customer.id());
		assertEquals(NAME, customer.name());
	}

	@Test
	void shouldCreateCustomerWithAddress() {
		Customer customer = CustomerFactory.createWithAddress(NAME, ADDRESS);
		assertNotNull(customer);
		assertNotNull(customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
	}

	@Test
	void shouldCreateActiveCustomerWithAddress() {
		Customer customer = CustomerFactory.createActive(NAME, ADDRESS);
		assertNotNull(customer);
		assertNotNull(customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertTrue(customer.isActive());
	}

	@Test
	void shouldCreateCustomerWithRewardPoints() {
		Customer customer = CustomerFactory.createWithRewardPoints(NAME, ADDRESS, 10, true);
		assertNotNull(customer);
		assertNotNull(customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertEquals(10, customer.rewardPoints());
		assertTrue(customer.isActive());
	}

	@Test
	void shouldCreateInactiveCustomerWithRewardPoints() {
		Customer customer = CustomerFactory.createWithRewardPoints(NAME, ADDRESS, 10, false);
		assertNotNull(customer);
		assertNotNull(customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertEquals(10, customer.rewardPoints());
		assertFalse(customer.isActive());
	}

	@Test
	void shouldInstanceCustomerWithId() {
		Customer customer = CustomerFactory.instance(ID, NAME);
		assertNotNull(customer);
		assertEquals(ID, customer.id());
		assertEquals(NAME, customer.name());
	}

	@Test
	void shouldInstanceCustomerWithAddress() {
		Customer customer = CustomerFactory.instanceWithAddress(ID, NAME, ADDRESS);
		assertNotNull(customer);
		assertEquals(ID, customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
	}

	@Test
	void shouldInstanceActiveCustomerWithAddress() {
		Customer customer = CustomerFactory.instanceActive(ID, NAME, ADDRESS);
		assertNotNull(customer);
		assertEquals(ID, customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertTrue(customer.isActive());
	}

	@Test
	void shouldInstanceCustomerWithRewardPoints() {
		Customer customer = CustomerFactory.instanceWithRewardPoints(ID, NAME, ADDRESS, 10, true);
		assertNotNull(customer);
		assertEquals(ID, customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertEquals(10, customer.rewardPoints());
		assertTrue(customer.isActive());
	}

	@Test
	void shouldInstanceInactiveCustomerWithRewardPoints() {
		Customer customer = CustomerFactory.instanceWithRewardPoints(ID, NAME, ADDRESS, 10, false);
		assertNotNull(customer);
		assertEquals(ID, customer.id());
		assertEquals(NAME, customer.name());
		assertEquals(ADDRESS, customer.address());
		assertEquals(10, customer.rewardPoints());
		assertFalse(customer.isActive());
	}
}