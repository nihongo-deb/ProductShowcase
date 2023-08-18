package org.nihongo_deb.ProductShowcase.DTO.Showcase;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
public class ShowcaseNewDTO {
    @Size(min = 2, max = 128, message = "name should be between 2 and 128 characters")
    @NotEmpty(message = "name should not be empty")
    private String name;
    @Size(min = 2, max = 64, message = "type should be between 2 and 64 characters")
    @NotEmpty(message = "type should not be empty")
    private String type;
    @Size(min = 2, max = 256, message = "address should be between 2 and 256 characters")
    @NotEmpty(message = "address should not be empty")
    private String address;

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
}
