package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.Mapping;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "seller")
public class SellerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nickname;
  private String email;
  private String password;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  /**
   * Relation
   */
  @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
  private List<ProductEntity> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public List<ProductEntity> getProducts() {
    return products;
  }

  public void setProducts(List<ProductEntity> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerEntity that = (SellerEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(nickname, that.nickname) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nickname, email, password, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "SellerEntity{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", createdAt=" + createdAt + '\'' +
            ", updatedAt=" + updatedAt + '\'' +
            ", products=" + products +
            '}';
  }
}
