package org.nihongo_deb.ProductShowcase.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "product_position")
    private Integer position;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_type")
    private String type;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "product_created_at")
    private LocalDateTime createdAt;

    @Column(name = "product_updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "showcase_id", referencedColumnName = "showcase_id")
    private Showcase owner;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Showcase getOwner() {
        return owner;
    }

    public void setOwner(Showcase owner) {
        this.owner = owner;
    }

    public void updateFields(Product product){
        this.price = product.getPrice();
        this.name = product.getName();
        this.type = product.getType();
        this.position = product.getPosition();
        this.updatedAt = LocalDateTime.now();
    }
}
