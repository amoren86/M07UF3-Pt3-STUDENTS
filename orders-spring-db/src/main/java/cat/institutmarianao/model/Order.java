package cat.institutmarianao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// NOTE: status
	public enum Status {
		PENDING, TRANSIT, DELIVERY, ABSENT, PENDING_COLLECTION, RETURNED
	}

	private Long reference;

	private User client;

	private Address deliveryAddress;

	private Date startDate;

	private Date deliveryDate;

	private Map<Item, Integer> items;

	private Status status = Status.PENDING;

	public Long getReference() {
		return reference;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	@Enumerated(EnumType.STRING)
	public void setStatus(Status status) {
		this.status = status;
	}

	public Map<Item, Integer> getItems() {
		if (items == null) {
			items = new HashMap<>();
		}
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = Objects.requireNonNullElse(items, new HashMap<>());
	}

	public Integer getTotalQuantity() {
		return items.values().stream().reduce(0, Integer::sum);
	}

	public Double getTotalAmount() {
		return items.entrySet().stream().map(e -> e.getKey().getPrice() * e.getValue()).reduce(0d, Double::sum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Order order) {
			return Objects.equals(reference, order.reference);
		}
		return false;
	}

}
