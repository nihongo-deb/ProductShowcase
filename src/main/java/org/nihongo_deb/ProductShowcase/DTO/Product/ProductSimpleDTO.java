package org.nihongo_deb.ProductShowcase.DTO.Product;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
public class ProductSimpleDTO {
    private UUID uuid;
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