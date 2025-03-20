package cat.institutmarianao.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_ADDRESS = 150;
	public static final int MAX_ZIP_CODE = 20;
	public static final int MAX_CITY = 150;
	public static final int MAX_STATE = 150;
	public static final int MAX_COUNTRY = 150;

	private Long id;

	private String recipientName;

	private String address;

	private String zipCode;

	private String city;

	private String state;

	private String country;

	public Long getId() {
		return id;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Address address) {
			return Objects.equals(id, address.id);
		}
		return false;
	}

}
