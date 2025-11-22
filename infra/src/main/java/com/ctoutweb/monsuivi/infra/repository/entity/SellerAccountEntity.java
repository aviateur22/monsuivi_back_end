package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "seller_account")
public class SellerAccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_activation_at")
  private ZonedDateTime accountActivationAt;

  @Column(name = "is_account_active")
  private Boolean isAccountActive;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  /**
   * Relation
   */
  @OneToOne
  @JoinColumn(name = "seller_id", referencedColumnName = "id")
  private SellerEntity seller;

  public SellerAccountEntity() {
  }

  public SellerAccountEntity(SellerEntity seller, Boolean isAccountActive, ZonedDateTime accountActivationAt) {
    this.seller = seller;
    this.isAccountActive = isAccountActive;
    this.accountActivationAt = accountActivationAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getAccountActivationAt() {
    return accountActivationAt;
  }

  public void setAccountActivationAt(ZonedDateTime accountActivationAt) {
    this.accountActivationAt = accountActivationAt;
  }

  public Boolean getAccountActive() {
    return isAccountActive;
  }

  public void setAccountActive(Boolean accountActive) {
    isAccountActive = accountActive;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerAccountEntity that = (SellerAccountEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(accountActivationAt, that.accountActivationAt) && Objects.equals(isAccountActive, that.isAccountActive) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(seller, that.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountActivationAt, isAccountActive, createdAt, updatedAt, seller);
  }

  @Override
  public String toString() {
    return "SellerAccountEntity{" +
            "id=" + id +
            ", accountActivationAt=" + accountActivationAt +
            ", isAccountActive=" + isAccountActive +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", seller=" + seller +
            '}';
  }
}
