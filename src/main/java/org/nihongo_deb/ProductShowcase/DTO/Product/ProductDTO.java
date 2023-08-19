package org.nihongo_deb.ProductShowcase.DTO.Product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseSimpleDTO;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO товара - response")
public class ProductDTO {
    @Schema(description = "UUID товара")
    private UUID uuid;

    @Schema(description = "Позиция товара")
    @Min(value = 1, message = "position should be between 1 and 10")
    @Max(value = 10, message = "position should be between 1 and 10")
    @NotNull(message = "position should not be empty")
    private Integer position;

    @Schema(description = "Название товара")
    @Size(min = 2, max = 256, message = "name should be between 2 and 256 characters")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Schema(description = "Тип товара")
    @Size(min = 2, max = 64, message = "name should be between 2 and 256 characters")
    @NotEmpty(message = "type should not be empty")
    private String type;

    @Schema(description = "Цена товара")
    @NotNull(message = "price should not be empty")
    private Double price;

    @Schema(description = "Дата и время создание товара")
    private LocalDateTime createdAt;

    @Schema(description = "Дата и время обновления товара")
    private LocalDateTime updatedAt;

    @Schema(description = "Данные витрины, которая владеет данным товаром (UUID и название витрины)")
    @NotNull(message = "price should not be empty")
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

    @Schema(description = "поле валидации цены", accessMode = Schema.AccessMode.READ_ONLY)
    @AssertTrue(message = "price should be greater then 0.01")
    public boolean isPriceNotValid() {
        return price >= 0.01;
    }
}
