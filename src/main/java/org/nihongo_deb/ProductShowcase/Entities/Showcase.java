package org.nihongo_deb.ProductShowcase.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */

@Entity
@Table(name = "showcase")
public class Showcase {
    @Id
    @Column(name = "showcase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "showcase_name")
    @Size(min = 2, max = 128, message = "name should be between 2 and 128 characters")
    @NotEmpty
    private String name;

    @Column(name = "showcase_address")
    @Size(min = 2, max = 256, message = "address should be between 2 and 256 characters")
    @NotEmpty
    private String address;

    @Column(name = "showcase_type")
    @Size(min = 2, max = 64, message = "type should be between 2 and 64 characters")
    @NotEmpty
    private String type; // TODO change to enum

    @Column(name = "showcase_created_at")
    private LocalDateTime createdAt;

    @Column(name = "showcase_updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "owner")
    private List<Product> products;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void updateFields(Showcase updatedShowcase) {
        this.name = updatedShowcase.getName();
        this.type = updatedShowcase.getType();
        this.address = updatedShowcase.getAddress();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Showcase{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", products=" + products +
                '}';
    }
}
