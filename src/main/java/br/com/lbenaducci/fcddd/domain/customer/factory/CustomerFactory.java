package br.com.lbenaducci.fcddd.domain.customer.factory;

import br.com.lbenaducci.fcddd.domain.customer.entity.Customer;
import br.com.lbenaducci.fcddd.domain.customer.datatype.Address;

import java.util.UUID;

public class CustomerFactory {

	CustomerFactory() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static Customer create(String name) {
		return new Customer(UUID.randomUUID(), name);
	}

	public static Customer createWithAddress(String name, Address address) {
		Customer customer = create(name);
		customer.changeAddress(address);
		return customer;
	}

	public static Customer createActive(String name, Address address) {
		Customer customer = createWithAddress(name, address);
		customer.activate();
		return customer;
	}

	public static Customer createWithRewardPoints(String name, Address address, int rewardPoints, boolean active) {
		Customer customer = createActive(name, address);
		customer.addRewardPoints(rewardPoints);
		if(!active) {
			customer.deactivate();
		}
		return customer;
	}

	public static Customer instance(UUID id, String name) {
		return new Customer(id, name);
	}

	public static Customer instanceWithAddress(UUID id, String name, Address address) {
		Customer customer = instance(id, name);
		customer.changeAddress(address);
		return customer;
	}

	public static Customer instanceActive(UUID id, String name, Address address) {
		Customer customer = instanceWithAddress(id, name, address);
		customer.activate();
		return customer;
	}

	public static Customer instanceWithRewardPoints(UUID id, String name, Address address, int rewardPoints, boolean active) {
		Customer customer = instanceActive(id, name, address);
		customer.addRewardPoints(rewardPoints);
		if(!active) {
			customer.deactivate();
		}
		return customer;
	}
}
