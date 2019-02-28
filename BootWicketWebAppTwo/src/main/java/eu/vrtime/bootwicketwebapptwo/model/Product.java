package eu.vrtime.bootwicketwebapptwo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRODUCT")
public class Product extends AbstractBaseEntity {

	@Column(nullable = false, unique = true, updatable = true)
	private String productId;

	@Column(nullable = false, unique = false, updatable = true)
	private ProductStatus productStatus;

	@Column(nullable = true, unique = false, updatable = true)
	private String description;

	public Product(final String productId, final ProductStatus productStatus) {
		this.productId = productId;
		this.productStatus = productStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productStatus=" + productStatus + ", description=" + description
				+ "]";
	}

}
