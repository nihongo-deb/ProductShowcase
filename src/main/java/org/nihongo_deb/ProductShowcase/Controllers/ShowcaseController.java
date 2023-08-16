package org.nihongo_deb.ProductShowcase.Controllers;

import org.nihongo_deb.ProductShowcase.DTO.ShowcaseDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Mappers.ShowcaseMapper;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 16.08.2023
 */
@RestController
@RequestMapping("/api/v1/showcase")
public class ShowcaseController {
    private final ShowcaseService showcaseService;
    private final ShowcaseMapper showcaseMapper;

    public ShowcaseController(ShowcaseService showcaseService, ShowcaseMapper showcaseMapper) {
        this.showcaseService = showcaseService;
        this.showcaseMapper = showcaseMapper;
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

    @GetMapping("/{uuid}")
    public Showcase getOneByUuid(@PathVariable("uuid") String uuid){
        // TODO добавить exception handler если возвращает null
        return this.showcaseService.findByUUID(UUID.fromString(uuid));
    }

    @PutMapping()
    public List<Showcase> filterShowcase(@RequestBody ShowcaseDTO showcaseDTO){
        if (showcaseDTO == null)
            return this.showcaseService.findAll();

        // TODO добавить exception handler если возвращает пустой список
        return this.showcaseService.findByDTO(showcaseDTO);
    }

    @PutMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody ShowcaseDTO showcaseDTO){
        LocalDateTime localDate = LocalDateTime.now();
        showcaseDTO.setCreatedAt(localDate);
        showcaseDTO.setUpdatedAt(localDate);

        Showcase showcase = this.showcaseMapper.DTOToObject(showcaseDTO);
        this.showcaseService.save(showcase);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable String uuid, @RequestBody ShowcaseDTO showcaseDTO){
        Showcase showcase = this.showcaseMapper.DTOToObject(showcaseDTO);

        //TODO ???
        showcase.setProducts(this.showcaseService.findByUUID(UUID.fromString(uuid)).getProducts());

        this.showcaseService.update(UUID.fromString(uuid), showcase);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
