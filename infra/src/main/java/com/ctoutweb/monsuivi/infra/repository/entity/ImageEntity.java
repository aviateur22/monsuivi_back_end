package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "image")
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "image_path")
  private String imagePath;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  /**
   * Relation
   */
  @ManyToOne
  @JoinColumn(name = "seller_id", nullable = false)
  private SellerEntity seller;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public SellerEntity getSeller() {
    return seller;
  }

  public void setSeller(SellerEntity seller) {
    this.seller = seller;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ImageEntity that = (ImageEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(imagePath, that.imagePath) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(seller, that.seller) && Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imagePath, createdAt, updatedAt, seller, product);
  }

  @Override
  public String toString() {
    return "ImageEntity{" +
            "id=" + id +
            ", imagePath='" + imagePath + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", seller=" + seller +
            ", product=" + product +
            '}';
  }
}
