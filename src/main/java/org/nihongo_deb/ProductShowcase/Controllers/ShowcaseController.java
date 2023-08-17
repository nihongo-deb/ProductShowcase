package org.nihongo_deb.ProductShowcase.Controllers;

import org.modelmapper.ModelMapper;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseFilterDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseNewDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
    private final ModelMapper modelMapper;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService,
                              ModelMapper modelMapper) {
        this.showcaseService = showcaseService;
        this.modelMapper = modelMapper;
    }

    // поиск витрины через параметры запроса
    @GetMapping()
    public List<ShowcaseDTO> getShowcases(
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "createdDateFrom", required = false) LocalDateTime createdDateFrom,
            @RequestParam(name = "createdDateTo", required = false) LocalDateTime createdDateTo,
            @RequestParam(name = "updatedDateFrom", required = false) LocalDateTime updatedDateFrom,
            @RequestParam(name = "updatedDateTo", required = false) LocalDateTime updatedDateTo){

        ShowcaseFilterDTO showcaseFilterDTO = new ShowcaseFilterDTO();

        showcaseFilterDTO.setType(type);
        showcaseFilterDTO.setAddress(address);
        showcaseFilterDTO.setCreatedDateFrom(createdDateFrom);
        showcaseFilterDTO.setCreatedDateTo(createdDateTo);
        showcaseFilterDTO.setCreatedDateFrom(updatedDateFrom);
        showcaseFilterDTO.setCreatedDateTo(updatedDateTo);

        List<ShowcaseDTO> showcaseDTOS = this.showcaseService.findByFilterDTO(showcaseFilterDTO)
                .stream()
                .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                .collect(Collectors.toList());

        return showcaseDTOS;
    }

    // поиск витрины через поля JSON-а (ShowcaseFilterDTO)
    @PutMapping()
    public List<ShowcaseDTO> filterShowcase(@RequestBody ShowcaseFilterDTO showcaseFilterDTO){
        List<ShowcaseDTO> showcaseDTOS;
        if (showcaseFilterDTO == null)
            showcaseDTOS = this.showcaseService.findAll()
                    .stream()
                    .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                    .collect(Collectors.toList());

        // TODO добавить exception handler если возвращает пустой список
        else
            showcaseDTOS = this.showcaseService.findByFilterDTO(showcaseFilterDTO)
                    .stream()
                    .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                    .collect(Collectors.toList());

        return showcaseDTOS;
    }

    @GetMapping("/{uuid}")
    public ShowcaseDTO getOneByUuid(@PathVariable("uuid") String uuid){
        // TODO добавить exception handler если возвращает null
        return modelMapper.map(this.showcaseService.findByUUID(UUID.fromString(uuid)), ShowcaseDTO.class);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody ShowcaseNewDTO showcaseNewDTO){
        LocalDateTime localDateTime = LocalDateTime.now();
        Showcase showcase = modelMapper.map(showcaseNewDTO, Showcase.class);
        showcase.setCreatedAt(localDateTime);
        showcase.setUpdatedAt(localDateTime);

        this.showcaseService.save(showcase);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable String uuid, @RequestBody ShowcaseDTO showcaseDTO){
        Showcase showcase = this.modelMapper.map(showcaseDTO, Showcase.class);

        //TODO ???
        showcase.setProducts(this.showcaseService.findByUUID(UUID.fromString(uuid)).getProducts());

        this.showcaseService.update(UUID.fromString(uuid), showcase);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){
        this.showcaseService.delete(UUID.fromString(uuid));

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
