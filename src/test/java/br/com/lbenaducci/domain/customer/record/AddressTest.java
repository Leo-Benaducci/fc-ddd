package br.com.lbenaducci.domain.customer.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
	private static final String STREET = "street";
	private static final String NUMBER = "number";
	private static final String ZIP_CODE = "zipCode";
	private static final String CITY = "city";

	@Test
	void shouldThrowExceptionWhenStreetIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Address(null, NUMBER, ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenStreetIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Address("", NUMBER, ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenStreetIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Address(" ", NUMBER, ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenNumberIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, null, ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenNumberIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, "", ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenNumberIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, " ", ZIP_CODE, CITY));
	}

	@Test
	void shouldThrowExceptionWhenZipCodeIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, null, CITY));
	}

	@Test
	void shouldThrowExceptionWhenZipCodeIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, "", CITY));
	}

	@Test
	void shouldThrowExceptionWhenZipCodeIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, " ", CITY));
	}

	@Test
	void shouldThrowExceptionWhenCityIsNull() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, ZIP_CODE, null));
	}

	@Test
	void shouldThrowExceptionWhenCityIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, ZIP_CODE, ""));
	}

	@Test
	void shouldThrowExceptionWhenCityIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new Address(STREET, NUMBER, ZIP_CODE, " "));
	}

	@Test
	void shouldCreateAddress() {
		Address address = new Address(STREET, NUMBER, ZIP_CODE, CITY);
		assertEquals(STREET, address.street());
		assertEquals(NUMBER, address.number());
		assertEquals(ZIP_CODE, address.zipCode());
		assertEquals(CITY, address.city());
	}
}