package org.nihongo_deb.ProductShowcase.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    @Min(value = 1, message = "position should be between 1 and 10")
    @Max(value = 10, message = "position should be between 1 and 10")
    @NotNull(message = "position should not be empty")
    private Integer position;

    @Column(name = "product_name")
    @Size(min = 2, max = 256, message = "name should be between 2 and 256 characters")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Column(name = "product_type")
    @Size(min = 2, max = 64, message = "name should be between 2 and 256 characters")
    @NotEmpty(message = "type should not be empty")
    private String type;

    @Column(name = "product_price")
    @NotNull(message = "price should not be empty")
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

    @AssertTrue(message = "price should be greater then 0.01")
    public boolean isPriceNotValid() {
        return price >= 0.01;
    }
}
