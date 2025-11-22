package com.ctoutweb.monsuivi.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "disable_login", schema = "sc_monsuivi")
public class DisableLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delay_login_until")
    private ZonedDateTime delayLoginUntil;

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

    public DisableLoginEntity() {
    }

    public DisableLoginEntity(SellerEntity seller, ZonedDateTime delayLoginUntil) {
        this.seller = seller;
        this.delayLoginUntil = delayLoginUntil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDelayLoginUntil() {
        return delayLoginUntil;
    }

    public void setDelayLoginUntil(ZonedDateTime delayLoginUntil) {
        this.delayLoginUntil = delayLoginUntil;
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

        if (o == null || getClass() != o.getClass()) return false;
        DisableLoginEntity that = (DisableLoginEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(delayLoginUntil, that.delayLoginUntil) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(seller, that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, delayLoginUntil, createdAt, updatedAt, seller);
    }

    @Override
    public String toString() {
        return "DisableLoginEntity{" +
                "id=" + id +
                ", delayLoginUntil=" + delayLoginUntil +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + seller +
                '}';
    }
}
