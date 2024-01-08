package br.com.lbenaducci.fcddd.domain.customer.entity;

import br.com.lbenaducci.fcddd.domain.customer.record.Address;

import java.util.UUID;

public class Customer {
	private final UUID id;
	private String name;
	private Address address;
	private boolean active;
	private int rewardPoints;

	public Customer(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.active = false;
		this.rewardPoints = 0;
		validate();
	}

	public String name() {
		return name;
	}

	public Address address() {
		return address;
	}

	public boolean isActive() {
		return active;
	}

	public int rewardPoints() {
		return rewardPoints;
	}

	private void validate() {
		if(id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or blank");
		}
	}

	public void changeName(String name) {
		String oldName = this.name;
		try {
			this.name = name;
			validate();
		} catch(IllegalArgumentException e) {
			this.name = oldName;
			throw e;
		}
	}

	public void changeAddress(Address address) {
		this.address = address;
	}

	public void activate() {
		if(address == null) {
			throw new IllegalStateException("Address cannot be null to activate a customer");
		}
		this.active = true;
	}

	public void deactivate() {
		this.active = false;
	}

	public void addRewardPoints(int points) {
		if(!isActive()) {
			throw new IllegalStateException("Cannot add reward points to inactive customer");
		}
		if(points <= 0) {
			throw new IllegalArgumentException("Cannot add negative or zero reward points");
		}
		this.rewardPoints += points;
	}
}
