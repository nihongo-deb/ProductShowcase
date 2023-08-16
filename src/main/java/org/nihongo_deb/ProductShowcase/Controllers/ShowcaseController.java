package org.nihongo_deb.ProductShowcase.Controllers;

import org.nihongo_deb.ProductShowcase.DTO.ShowcaseDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@RestController
@RequestMapping("/api/v1/showcase")
public class ShowcaseController {
    private final ShowcaseService showcaseService;

    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    @GetMapping()
    public List<Showcase> getShowcases(
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "createdDateFrom", required = false) LocalDateTime createdDateFrom,
            @RequestParam(name = "createdDateTo", required = false) LocalDateTime createdDateTo,
            @RequestParam(name = "updatedDateFrom", required = false) LocalDateTime updatedDateFrom,
            @RequestParam(name = "updatedDateTo", required = false) LocalDateTime updatedDateTo){

        ShowcaseDTO showcaseDTO = new ShowcaseDTO();

        showcaseDTO.setType(type);
        showcaseDTO.setAddress(address);
        showcaseDTO.setCreatedDateFrom(createdDateFrom);
        showcaseDTO.setCreatedDateTo(createdDateTo);
        showcaseDTO.setCreatedDateFrom(updatedDateFrom);
        showcaseDTO.setCreatedDateTo(updatedDateTo);

        return this.showcaseService.findByDTO(showcaseDTO);
    }

    @PutMapping()
    public List<Showcase> filterShowcase(@RequestBody ShowcaseDTO showcaseDTO){
        if (showcaseDTO == null)
            return this.showcaseService.findAll();
        return this.showcaseService.findByDTO(showcaseDTO);
    }
}
