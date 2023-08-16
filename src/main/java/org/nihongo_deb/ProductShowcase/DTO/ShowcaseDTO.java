package org.nihongo_deb.ProductShowcase.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
public class ShowcaseDTO {
    private String name;
    private String type;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
    private LocalDateTime updatedDateFrom;
    private LocalDateTime updatedDateTo;

    public ShowcaseDTO() {

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

    @Override
    public String toString() {
        return "ShowcaseDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createdDateFrom=" + createdDateFrom +
                ", createdDateTo=" + createdDateTo +
                ", updatedDateFrom=" + updatedDateFrom +
                ", updatedDateTo=" + updatedDateTo +
                '}';
    }
}
