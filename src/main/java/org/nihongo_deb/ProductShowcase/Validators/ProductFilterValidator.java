package org.nihongo_deb.ProductShowcase.Validators;

import org.nihongo_deb.ProductShowcase.DTO.Product.ProductFilterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 19.08.2023
 */
@Component
public class ProductFilterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductFilterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductFilterDTO filterDTO = (ProductFilterDTO) target;

        Double priceFrom = filterDTO.getPriceFrom();
        Double priceTo = filterDTO.getPriceTo();

        if(priceFrom == null || priceTo == null)
            return;

        if (priceFrom > priceTo)
            errors.rejectValue("priceFrom", "", "is greater then priceTo");
    }
}
