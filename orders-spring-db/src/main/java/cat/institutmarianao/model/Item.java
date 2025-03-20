package cat.institutmarianao.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_NAME = 50;
	public static final int MAX_DESCRIPTION = 250;

	private Long reference;

	private String name;

	private String description;

	private Double price;

	private byte[] image;

	public Long getReference() {
		return reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(image);
	}

	public void setBase64Image(String b64Image) {
		image = Base64.getDecoder().decode(b64Image);
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
		if (object instanceof Item item) {
			return Objects.equals(reference, item.reference);
		}
		return false;
	}

}
