package org.nihongo_deb.ProductShowcase.Validators;

import org.nihongo_deb.ProductShowcase.DTO.Product.ProductNewDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 18.08.2023
 */
@Component
public class NewProductValidator implements Validator {
    private final ShowcaseService showcaseService;

    @Autowired
    public NewProductValidator(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductNewDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductNewDTO productNewDTO = (ProductNewDTO) target;

        if (showcaseService.findByUUID(productNewDTO.getOwner()) == null){
            System.out.println(true);
            errors.rejectValue("owner", "", "no showcases with this uuid exist");
        } else System.out.println(false);
    }
}
