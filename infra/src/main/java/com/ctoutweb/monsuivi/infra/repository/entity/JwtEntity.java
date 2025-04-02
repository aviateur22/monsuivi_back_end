package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "jwt")
public class JwtEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  @Column(name = "jwt_token")
  private String jwtToken;

  @Column(name = "jwt_id")
  private String jwtId;

  @Column(name = "is_valid")
  private Boolean isValid;

  @Column(name = "expired_at")
  private ZonedDateTime expiredAt;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public String getJwtId() {
    return jwtId;
  }

  public void setJwtId(String jwtId) {
    this.jwtId = jwtId;
  }

  public Boolean getValid() {
    return isValid;
  }

  public void setValid(Boolean valid) {
    isValid = valid;
  }

  public ZonedDateTime getExpiredAt() {
    return expiredAt;
  }

  public void setExpiredAt(ZonedDateTime expiredAt) {
    this.expiredAt = expiredAt;
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
    JwtEntity jwtEntity = (JwtEntity) o;
    return Objects.equals(id, jwtEntity.id) && Objects.equals(email, jwtEntity.email) && Objects.equals(jwtToken, jwtEntity.jwtToken) && Objects.equals(jwtId, jwtEntity.jwtId) && Objects.equals(isValid, jwtEntity.isValid) && Objects.equals(expiredAt, jwtEntity.expiredAt) && Objects.equals(createdAt, jwtEntity.createdAt) && Objects.equals(updatedAt, jwtEntity.updatedAt) && Objects.equals(seller, jwtEntity.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, jwtToken, jwtId, isValid, expiredAt, createdAt, updatedAt, seller);
  }

  @Override
  public String toString() {
    return "JwtEntity{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", jwtToken='" + jwtToken + '\'' +
            ", jwtId='" + jwtId + '\'' +
            ", isValid=" + isValid +
            ", expiredAt=" + expiredAt +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", seller=" + seller +
            '}';
  }
}
