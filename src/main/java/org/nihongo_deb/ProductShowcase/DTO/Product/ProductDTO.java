package org.nihongo_deb.ProductShowcase.DTO.Product;

import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseSimpleDTO;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
public class ProductDTO {
    private UUID uuid;
    private Integer position;
    private String name;
    private String type;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ShowcaseSimpleDTO owner;

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

    public ShowcaseSimpleDTO getOwner() {
        return owner;
    }

    public void setOwner(ShowcaseSimpleDTO owner) {
        this.owner = owner;
    }
}
