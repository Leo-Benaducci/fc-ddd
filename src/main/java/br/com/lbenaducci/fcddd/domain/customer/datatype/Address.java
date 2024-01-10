package br.com.lbenaducci.fcddd.domain.customer.datatype;

public record Address(
		String street,
		String number,
		String zipCode,
		String city
) {
	public Address {
		if(street == null || street.isBlank()) {
			throw new IllegalArgumentException("Street cannot be null or blank");
		}
		if(number == null || number.isBlank()) {
			throw new IllegalArgumentException("Number cannot be null or blank");
		}
		if(zipCode == null || zipCode.isBlank()) {
			throw new IllegalArgumentException("Zip code cannot be null or blank");
		}
		if(city == null || city.isBlank()) {
			throw new IllegalArgumentException("City cannot be null or blank");
		}
	}
}
