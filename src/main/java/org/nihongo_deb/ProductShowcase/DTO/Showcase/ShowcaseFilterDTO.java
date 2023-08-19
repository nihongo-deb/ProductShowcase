package org.nihongo_deb.ProductShowcase.DTO.Showcase;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO фильтра витрины - request (все поля опциональны)")
public class ShowcaseFilterDTO {
    @Schema(description = "Название витрины")
    private String name;
    @Schema(description = "Тип витрины")
    private String type;
    @Schema(description = "Адрес витрины")
    private String address;
    @Schema(description = "Дата и время начала диапазона создания витрины")
    private LocalDateTime createdDateFrom;
    @Schema(description = "Дата и время конца диапазона создания витрины")
    private LocalDateTime createdDateTo;
    @Schema(description = "Дата и время начала диапазона обновления витрины")
    private LocalDateTime updatedDateFrom;
    @Schema(description = "Дата и время конца диапазона обновления витрины")
    private LocalDateTime updatedDateTo;

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

    public LocalDateTime getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(LocalDateTime createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public LocalDateTime getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(LocalDateTime createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public LocalDateTime getUpdatedDateFrom() {
        return updatedDateFrom;
    }

    public void setUpdatedDateFrom(LocalDateTime updatedDateFrom) {
        this.updatedDateFrom = updatedDateFrom;
    }

    public LocalDateTime getUpdatedDateTo() {
        return updatedDateTo;
    }

    public void setUpdatedDateTo(LocalDateTime updatedDateTo) {
        this.updatedDateTo = updatedDateTo;
    }
}
