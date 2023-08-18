package org.nihongo_deb.ProductShowcase.Controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductDTO;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductFilterDTO;
import org.nihongo_deb.ProductShowcase.DTO.Product.ProductNewDTO;
import org.nihongo_deb.ProductShowcase.Entities.Product;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ProductService;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.nihongo_deb.ProductShowcase.Util.ErrorResponces.MyErrorResponse;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ProductNotCreatedException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ProductNotFoundException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ProductNotUpdatedException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ShowcaseNotFoundException;
import org.nihongo_deb.ProductShowcase.Validators.NewProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private final NewProductValidator newProductValidator;

    @Autowired
    public ProductController(ProductService productService, ShowcaseService showcaseService, ModelMapper modelMapper, NewProductValidator newProductValidator) {
        this.productService = productService;
        this.showcaseService = showcaseService;
        this.modelMapper = modelMapper;
        this.newProductValidator = newProductValidator;
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

    @PostMapping()
    private ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductNewDTO productNewDTO, BindingResult bindingResult){
        newProductValidator.validate(productNewDTO, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getFieldErrors()
                    .forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()).append(";"));

            throw new ProductNotCreatedException(stringBuilder.toString());
        }

        Product product = this.modelMapper.map(productNewDTO, Product.class);
        this.productService.save(product);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable String uuid, @RequestBody @Valid ProductNewDTO productNewDTO, BindingResult bindingResult){
        newProductValidator.validate(productNewDTO, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getFieldErrors()
                    .forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()).append(";"));

            throw new ProductNotUpdatedException(stringBuilder.toString());
        }

        Product updatedProduct = this.modelMapper.map(productNewDTO, Product.class);
        this.productService.update(UUID.fromString(uuid), updatedProduct);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){
        this.productService.delete(UUID.fromString(uuid));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ProductNotCreatedException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ProductNotUpdatedException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ProductNotFoundException e){
        MyErrorResponse response = new MyErrorResponse("product not found, check uuid");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ShowcaseNotFoundException e){
        MyErrorResponse response = new MyErrorResponse("showcase not found, check uuid");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
