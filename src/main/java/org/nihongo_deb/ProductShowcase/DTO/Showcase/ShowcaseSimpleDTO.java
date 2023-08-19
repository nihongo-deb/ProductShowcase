package org.nihongo_deb.ProductShowcase.DTO.Showcase;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO минимальных данных о витрине - response")
public class ShowcaseSimpleDTO {
    @Schema(description = "UUID витрины")
    private UUID uuid;
    @Schema(description = "Название витрины")
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
