package org.nihongo_deb.ProductShowcase.DTO.Product;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
public class ProductFilterDTO {
    private String type;
    private Double priceFrom;
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
