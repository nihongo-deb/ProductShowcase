package org.nihongo_deb.ProductShowcase.DTO.Product;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@Schema(description = "DTO фильтра товаров - request")
public class ProductFilterDTO {
    @Schema(description = "Тип товара")
    private String type;
    @Schema(description = "Начало диапазона цены")
    private Double priceFrom;
    @Schema(description = "Конец диапазона цены")
    private Double priceTo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }
}
