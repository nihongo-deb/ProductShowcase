package org.nihongo_deb.ProductShowcase.Validators;

import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseFilterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 19.08.2023
 */
@Component
public class ShowcaseFilterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ShowcaseFilterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShowcaseFilterDTO showcaseFilterDTO = (ShowcaseFilterDTO) target;
        LocalDateTime createdDateFrom = showcaseFilterDTO.getCreatedDateFrom();
        LocalDateTime createdDateTo = showcaseFilterDTO.getCreatedDateTo();
        LocalDateTime updatedDateFrom = showcaseFilterDTO.getUpdatedDateFrom();
        LocalDateTime updatedDateTo = showcaseFilterDTO.getUpdatedDateTo();

        if (createdDateFrom != null && createdDateTo != null)
            if (createdDateFrom.isAfter(createdDateTo))
                errors.rejectValue("createdDateFrom", "", "is greater then createdDateTo");
        if (updatedDateFrom != null && updatedDateTo != null)
            if (updatedDateFrom.isAfter(updatedDateTo))
                errors.rejectValue("updatedDateFrom", "", "is greater then updatedDateTo");

    }
}
