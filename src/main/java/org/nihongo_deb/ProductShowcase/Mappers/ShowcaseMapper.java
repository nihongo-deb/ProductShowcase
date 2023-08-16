package org.nihongo_deb.ProductShowcase.Mappers;

import org.nihongo_deb.ProductShowcase.DTO.ShowcaseDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.springframework.stereotype.Component;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@Component
public class ShowcaseMapper {
    public Showcase DTOToObject(ShowcaseDTO showcaseDTO){
        Showcase showcase = new Showcase();

        showcase.setName(showcaseDTO.getName());
        showcase.setType(showcaseDTO.getType());
        showcase.setAddress(showcaseDTO.getAddress());
        showcase.setCreatedAt(showcaseDTO.getCreatedAt());
        showcase.setUpdatedAt(showcaseDTO.getUpdatedAt());

        return showcase;
    }

    public ShowcaseDTO objectToDTO(Showcase showcase){
        ShowcaseDTO showcaseDTO = new ShowcaseDTO();

        showcaseDTO.setName(showcase.getName());
        showcaseDTO.setType(showcase.getType());
        showcaseDTO.setAddress(showcase.getAddress());
        showcaseDTO.setCreatedAt(showcase.getCreatedAt());
        showcaseDTO.setUpdatedAt(showcase.getUpdatedAt());

        return showcaseDTO;
    }
}
