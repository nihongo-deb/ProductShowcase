package org.nihongo_deb.ProductShowcase.Controllers;

import org.modelmapper.ModelMapper;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductDTO;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductFilterDTO;
import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ProductService;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 17.08.2023
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ShowcaseService showcaseService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ShowcaseService showcaseService, ModelMapper modelMapper) {
        this.productService = productService;
        this.showcaseService = showcaseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{showcaseUUID}")
    public List<ProductDTO> getAllProductsInShowcase(@PathVariable String showcaseUUID){
        Showcase showcase = this.showcaseService.findByUUID(UUID.fromString(showcaseUUID));

        List<ProductDTO> productDTOS = this.productService.findByShowcase(showcase)
                .stream()
                .map((element) -> modelMapper.map(element, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOS;
    }

    @PutMapping("/{showcaseUUID}")
    public List<ProductDTO> getProductsByFilterDTO(@PathVariable String showcaseUUID, @RequestBody ProductFilterDTO filterDTO){
        Showcase showcase = this.showcaseService.findByUUID(UUID.fromString(showcaseUUID));

        List<ProductDTO> productDTOS = this.productService.findByFilterDTO(showcase, filterDTO)
                .stream()
                .map((element) -> modelMapper.map(element, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOS;
    }
}
