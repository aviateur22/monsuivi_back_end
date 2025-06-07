package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "product_name")
  private String productName;
  @Column(name = "product_purchase_price")
  private Double productPurchasePrice;
  @Column(name = "product_sold_price")
  private Double productSoldPrice;
  @Column(name = "product_category")
  private String productCategory;
  @Column(name = "product_status")
  private String productStatus;
  @Column(name = "product_sold_at")
  private LocalDate productSoldAt;
  @Column(name = "is_actif")
  private Boolean isActif;
  @Column(name = "product_buy_at")
  private LocalDate productBuyAt;
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
  @JoinColumn(name="seller_id", nullable=false)
  private SellerEntity seller;

  @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
  private List<ImageEntity> images;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getProductPurchasePrice() {
    return productPurchasePrice;
  }

  public void setProductPurchasePrice(Double productPurchasePrice) {
    this.productPurchasePrice = productPurchasePrice;
  }

  public Double getProductSoldPrice() {
    return productSoldPrice;
  }

  public void setProductSoldPrice(Double productSoldPrice) {
    this.productSoldPrice = productSoldPrice;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(String productCategory) {
    this.productCategory = productCategory;
  }

  public LocalDate getProductSoldAt() {
    return productSoldAt;
  }

  public void setProductSoldAt(LocalDate productSoldAt) {
    this.productSoldAt = productSoldAt;
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

  public LocalDate getProductBuyAt() {
    return productBuyAt;
  }

  public void setProductBuyAt(LocalDate productBuyAt) {
    this.productBuyAt = productBuyAt;
  }

  public List<ImageEntity> getImages() {
    return images;
  }

  public void setImages(List<ImageEntity> images) {
    this.images = images;
  }

  public String getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(String productStatus) {
    this.productStatus = productStatus;
  }

  public Boolean getIsActif() {
    return isActif;
  }

  public void setIsActif(Boolean actif) {
    isActif = actif;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductEntity product = (ProductEntity) o;
    return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(productPurchasePrice, product.productPurchasePrice) && Objects.equals(productSoldPrice, product.productSoldPrice) && Objects.equals(productCategory, product.productCategory) && Objects.equals(productSoldAt, product.productSoldAt) && Objects.equals(productBuyAt, product.productBuyAt) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt) && Objects.equals(seller, product.seller) && Objects.equals(images, product.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productName, productPurchasePrice, productSoldPrice, productCategory, productSoldAt, productBuyAt, createdAt, updatedAt, seller, images);
  }

  @Override
  public String toString() {
    return "ProductEntity{" +
            "id=" + id +
            ", productName='" + productName + '\'' +
            ", productPurchasePrice=" + productPurchasePrice +
            ", productSoldPrice=" + productSoldPrice +
            ", productCategory='" + productCategory + '\'' +
            ", productSoldAt=" + productSoldAt +
            ", productBuyAt=" + productBuyAt +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            //", seller=" + seller +
            ", images=" + images +
            '}';
  }
}
