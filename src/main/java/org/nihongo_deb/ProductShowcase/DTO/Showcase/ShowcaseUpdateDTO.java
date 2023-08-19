package org.nihongo_deb.ProductShowcase.DTO.Showcase;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductOnlyUuidDTO;

import java.util.List;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 19.08.2023
 */
@Schema(description = "DTO обновлённой витрины - request")
public class ShowcaseUpdateDTO {
    @Schema(description = "Новое название витрины")
    @Size(min = 2, max = 128, message = "name should be between 2 and 128 characters")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Schema(description = "Новый тип витрины")
    @Size(min = 2, max = 64, message = "type should be between 2 and 64 characters")
    @NotEmpty(message = "type should not be empty")
    private String type;

    @Schema(description = "Новый адрес витрины")
    @Size(min = 2, max = 256, message = "address should be between 2 and 256 characters")
    @NotEmpty(message = "address should not be empty")
    private String address;

    @Schema(description = "UUID продуктов, которые будут принадлежать данной витрине")
    private List<ProductOnlyUuidDTO> products;

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

    public List<ProductOnlyUuidDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOnlyUuidDTO> products) {
        this.products = products;
    }
}
