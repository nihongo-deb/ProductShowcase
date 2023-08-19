package org.nihongo_deb.ProductShowcase.Validators;

import org.nihongo_deb.ProductShowcase.DTO.Product.ProductOnlyUuidDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseUpdateDTO;
import org.nihongo_deb.ProductShowcase.Services.ProductService;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 19.08.2023
 */
@Component
public class UpdateShowcaseValidator implements Validator {
    private final ProductService productService;

    @Autowired
    public UpdateShowcaseValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ShowcaseUpdateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShowcaseUpdateDTO showcaseUpdateDTO = (ShowcaseUpdateDTO) target;
        if (showcaseUpdateDTO.getProducts() == null)
            return;
        if (showcaseUpdateDTO.getProducts().isEmpty())
            return;

        for (ProductOnlyUuidDTO product : showcaseUpdateDTO.getProducts()){
            try {
                productService.findById(product.getUuid());
            }catch (ProductNotFoundException e){
                errors.rejectValue("products", "", product.getUuid().toString() + " - product with such uuid does not exist");
            }
        }
    }
}
