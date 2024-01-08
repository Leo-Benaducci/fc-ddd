package br.com.lbenaducci.domain.customer.entity;

import br.com.lbenaducci.domain.customer.record.Address;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "John";
	private static final Address ADDRESS = new Address("street", "number", "zipCode", "city");

	@Test
	void shouldThrowExceptionWhenIdIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Customer(null, NAME));
	}

	@Test
	void shouldThrowExceptionWhenNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Customer(ID, null));
	}

	@Test
	void shouldThrowExceptionWhenNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Customer(ID, ""));
	}

	@Test
	void shouldThrowExceptionWhenNameIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Customer(ID, " "));
	}

	@Test
	void shouldChangeName() {
		Customer customer = new Customer(ID, NAME);
		customer.changeName("Jane");
		assertEquals("Jane", customer.name());
	}

	@Test
	void shouldThrowExceptionWhenNameIsBlankWhenChangeName() {
		Customer customer = new Customer(ID, NAME);
		assertThrows(IllegalArgumentException.class, () -> customer.changeName(" "));
		assertEquals(NAME, customer.name());
	}

	@Test
	void shouldChangeAddress() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		assertEquals(ADDRESS, customer.address());
	}

	@Test
	void shouldActivate() {
		Customer customer = new Customer(ID, NAME);
		assertFalse(customer.isActive());
		customer.changeAddress(ADDRESS);
		customer.activate();
		assertTrue(customer.isActive());
	}

	@Test
	void shouldThrowExceptionWhenAddressIsNullWhenActivate() {
		Customer customer = new Customer(ID, NAME);
		assertFalse(customer.isActive());
		assertThrows(IllegalStateException.class, customer::activate);
		assertFalse(customer.isActive());
	}

	@Test
	void shouldDeactivate() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		assertTrue(customer.isActive());
		customer.deactivate();
		assertFalse(customer.isActive());
	}

	@Test
	void shouldAddRewardPoints() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		assertEquals(0, customer.rewardPoints());
		customer.addRewardPoints(10);
		assertEquals(10, customer.rewardPoints());
		customer.addRewardPoints(5);
		assertEquals(15, customer.rewardPoints());
	}

	@Test
	void shouldThrowExceptionWhenAddRewardPointsInInactiveCustomer() {
		Customer customer = new Customer(ID, NAME);
		assertThrows(IllegalStateException.class, () -> customer.addRewardPoints(10));
		assertEquals(0, customer.rewardPoints());
	}

	@Test
	void shouldThrowExceptionWhenAddNegativeRewardPoints() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		assertThrows(IllegalArgumentException.class, () -> customer.addRewardPoints(-10));
		assertEquals(0, customer.rewardPoints());
	}

	@Test
	void shouldThrowExceptionWhenAddZeroRewardPoints() {
		Customer customer = new Customer(ID, NAME);
		customer.changeAddress(ADDRESS);
		customer.activate();
		assertThrows(IllegalArgumentException.class, () -> customer.addRewardPoints(0));
		assertEquals(0, customer.rewardPoints());
	}
}