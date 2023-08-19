package org.nihongo_deb.ProductShowcase.DTO.Product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 19.08.2023
 */
@Schema(description = "DTO хранящая только UUID товара (нужна для обновления витрины)")
public class ProductOnlyUuidDTO {
    @Schema(description = "UUID товара")
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
