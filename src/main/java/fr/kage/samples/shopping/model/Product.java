package fr.kage.samples.shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.common.hash.HashCodes;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int amount;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj instanceof Product) {
			Product other = (Product) obj;
			return getId() == other.getId()
					&& Objects.equal(getName(), other.getName())
					&& getAmount() == other.getAmount()
					&& Objects.equal(getCategory(), other.getCategory());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getName(), getAmount(), getCategory());
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(Product.class)
				.add("id", getId())
				.add("name", getName())
				.add("amount", getAmount())
				.add("category", getCategory())
				.toString();
	}
	
	public void updateFrom(Product other) {
		setName(other.getName());
		setAmount(other.amount);
		setCategory(other.category);
	}
}
