package fr.kage.samples.shopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

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

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj instanceof Category) {
			Category other = (Category) obj;
			return getId() == other.getId()
					&& Objects.equal(getName(), other.getName());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getName());
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(Category.class)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}

	public void updateFrom(Category category) {
		setName(category.getName());
	}
}
