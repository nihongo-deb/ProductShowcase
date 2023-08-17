package org.nihongo_deb.ProductShowcase.DTO.Showcase;

import org.nihongo_deb.ProductShowcase.DTO.Product.ProductSimpleDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
public class ShowcaseDTO {
    private UUID uuid;
    private String name;
    private String type;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<ProductSimpleDTO> products;


    public ShowcaseDTO() {

    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<ProductSimpleDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSimpleDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShowcaseDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
