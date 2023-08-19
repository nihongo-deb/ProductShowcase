package org.nihongo_deb.ProductShowcase.DTO.Product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO минимальных данных о товаре - response")
public class ProductSimpleDTO {
    @Schema(description = "UUID товара")
    private UUID uuid;
    @Schema(description = "Название товара")
    private String name;

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
}
