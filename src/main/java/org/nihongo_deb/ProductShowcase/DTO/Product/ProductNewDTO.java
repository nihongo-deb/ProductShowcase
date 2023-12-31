package org.nihongo_deb.ProductShowcase.DTO.Product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO нового или обновлённого товара - request")
public class ProductNewDTO {
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

    @Schema(description = "UUID существующей витрины (не обязателен)")
    private UUID owner;

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

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Schema(description = "поле валидации цены", accessMode = Schema.AccessMode.READ_ONLY)
    @AssertTrue(message = "price should be greater then 0.01")
    public boolean isPriceNotValid() {
        if (price == null)
            return false;
        return this.price >= 0.01;
    }
}
