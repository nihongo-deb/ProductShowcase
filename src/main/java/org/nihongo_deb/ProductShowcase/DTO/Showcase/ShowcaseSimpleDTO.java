package org.nihongo_deb.ProductShowcase.DTO.Showcase;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
public class ShowcaseSimpleDTO {
    @NotNull
    @org.hibernate.validator.constraints.UUID
    private UUID uuid;

    @NotNull
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
