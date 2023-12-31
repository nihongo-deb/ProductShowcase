package org.nihongo_deb.ProductShowcase.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.nihongo_deb.ProductShowcase.Util.Exceptions.*;
import org.nihongo_deb.ProductShowcase.Validators.NewProductValidator;
import org.nihongo_deb.ProductShowcase.Validators.ProductFilterValidator;
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
@Tag(name = "Продукты", description = "Позволяет управлять продуктами")
public class ProductController {
    private final ProductService productService;
    private final ShowcaseService showcaseService;
    private final ModelMapper modelMapper;

    private final NewProductValidator newProductValidator;
    private final ProductFilterValidator productFilterValidator;

    @Autowired
    public ProductController(ProductService productService, ShowcaseService showcaseService, ModelMapper modelMapper, NewProductValidator newProductValidator, ProductFilterValidator productFilterValidator) {
        this.productService = productService;
        this.showcaseService = showcaseService;
        this.modelMapper = modelMapper;
        this.newProductValidator = newProductValidator;
        this.productFilterValidator = productFilterValidator;
    }

    @Operation(
            summary = "Получение списка продуктов",
            description = "Получение списка продуктов по UUID (PathVariable) витрины")
    @GetMapping("/{showcaseUUID}")
    public List<ProductDTO> getAllProductsInShowcase(@PathVariable String showcaseUUID){
        Showcase showcase = this.showcaseService.findByUUID(UUID.fromString(showcaseUUID));

        List<ProductDTO> productDTOS = this.productService.findByShowcase(showcase)
                .stream()
                .map((element) -> modelMapper.map(element, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOS;
    }

    @Operation(
            summary = "Получение списка продуктов",
            description = "Получение списка продуктов по UUID (PathVariable) витрины и их фильтрация по полям тела запроса (см. ProductFilterDTO)")
    @PutMapping("/{showcaseUUID}")
    public List<ProductDTO> getProductsByFilterDTO(@PathVariable String showcaseUUID, @RequestBody @Valid ProductFilterDTO filterDTO, BindingResult bindingResult){
        Showcase showcase = this.showcaseService.findByUUID(UUID.fromString(showcaseUUID));
        productFilterValidator.validate(filterDTO, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getFieldErrors().forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()));

            throw new ProductFilterException(stringBuilder.toString());
        }

        List<ProductDTO> productDTOS = this.productService.findByFilterDTO(showcase, filterDTO)
                .stream()
                .map((element) -> modelMapper.map(element, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOS;
    }
    @Operation(
            summary = "Создание продукта",
            description = "Создание происходит по полям, переданные в теле запроса (см. ProductNewDTO)")
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

    @Operation(
            summary = "Обновление продукта",
            description = "Обновление происходит по UUID (PathVariable) продукта и по полям, переданные в теле запроса (см. ProductNewDTO)")
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

    @Operation(
            summary = "Удаление продукта",
            description = "Удаление происходит по UUID (PathVariable) продукта")
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

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ProductFilterException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
