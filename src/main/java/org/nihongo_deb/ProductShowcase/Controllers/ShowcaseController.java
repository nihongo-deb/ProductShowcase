package org.nihongo_deb.ProductShowcase.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseFilterDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseNewDTO;
import org.nihongo_deb.ProductShowcase.DTO.Showcase.ShowcaseUpdateDTO;
import org.nihongo_deb.ProductShowcase.Entities.Showcase;
import org.nihongo_deb.ProductShowcase.Services.ShowcaseService;
import org.nihongo_deb.ProductShowcase.Util.ErrorResponces.MyErrorResponse;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ShowcaseFilterException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ShowcaseNotCreatedException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ShowcaseNotFoundException;
import org.nihongo_deb.ProductShowcase.Util.Exceptions.ShowcaseNotUpdatedException;
import org.nihongo_deb.ProductShowcase.Validators.ShowcaseFilterValidator;
import org.nihongo_deb.ProductShowcase.Validators.UpdateShowcaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@Tag(name = "Витрины", description = "Позволяет управлять витринами")
public class ShowcaseController {
    private final ShowcaseService showcaseService;
    private final ModelMapper modelMapper;

    private final UpdateShowcaseValidator updateShowcaseValidator;
    private final ShowcaseFilterValidator showcaseFilterValidator;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService, ModelMapper modelMapper, UpdateShowcaseValidator updateShowcaseValidator, ShowcaseFilterValidator showcaseFilterValidator) {
        this.showcaseService = showcaseService;
        this.modelMapper = modelMapper;
        this.updateShowcaseValidator = updateShowcaseValidator;
        this.showcaseFilterValidator = showcaseFilterValidator;
    }

    // поиск витрины через параметры запроса
    @Operation(
            summary = "Получение списка витрин",
            description = "Фильтрация осуществляется по параметрам запроса. Deprecated так как отсутствует валидация параметров запроса")
    @Deprecated()
    @GetMapping()
    public List<ShowcaseDTO> getShowcases(
            @RequestParam(name = "type", required = false) @Parameter(description = "Тип витрины") String type,
            @RequestParam(name = "address", required = false) @Parameter(description = "Адрес витрины") String address,
            @RequestParam(name = "createdDateFrom", required = false) @Parameter(description = "Начало промежутка создания витрины") LocalDateTime createdDateFrom,
            @RequestParam(name = "createdDateTo", required = false) @Parameter(description = "Конец промежутка создания витрины") LocalDateTime createdDateTo,
            @RequestParam(name = "updatedDateFrom", required = false) @Parameter(description = "Начало промежутка обновления витрины") LocalDateTime updatedDateFrom,
            @RequestParam(name = "updatedDateTo", required = false) @Parameter(description = "Конец промежутка обновления витрины") LocalDateTime updatedDateTo){

        ShowcaseFilterDTO showcaseFilterDTO = new ShowcaseFilterDTO();

        showcaseFilterDTO.setType(type);
        showcaseFilterDTO.setAddress(address);
        showcaseFilterDTO.setCreatedDateFrom(createdDateFrom);
        showcaseFilterDTO.setCreatedDateTo(createdDateTo);
        showcaseFilterDTO.setUpdatedDateFrom(updatedDateFrom);
        showcaseFilterDTO.setUpdatedDateTo(updatedDateTo);

        List<ShowcaseDTO> showcaseDTOS = this.showcaseService.findByFilterDTO(showcaseFilterDTO)
                .stream()
                .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                .collect(Collectors.toList());

        return showcaseDTOS;
    }

    // поиск витрины через поля JSON-а (ShowcaseFilterDTO)
    @Operation(
            summary = "Получение списка витрин",
            description = "Фильтрация осуществляется по полям тела запроса (см. ShowcaseFilterDTO)")
    @PutMapping()
    public List<ShowcaseDTO> filterShowcase(@RequestBody @Valid ShowcaseFilterDTO showcaseFilterDTO, BindingResult bindingResult){
        List<ShowcaseDTO> showcaseDTOS;
        if (showcaseFilterDTO == null)
            showcaseDTOS = this.showcaseService.findAll()
                    .stream()
                    .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                    .collect(Collectors.toList());
        else {
            showcaseFilterValidator.validate(showcaseFilterDTO, bindingResult);

            if (bindingResult.hasErrors()){
                StringBuilder stringBuilder = new StringBuilder();
                bindingResult.getFieldErrors().forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()));

                throw new ShowcaseFilterException(stringBuilder.toString());
            }

            showcaseDTOS = this.showcaseService.findByFilterDTO(showcaseFilterDTO)
                    .stream()
                    .map((element) -> modelMapper.map(element, ShowcaseDTO.class))
                    .collect(Collectors.toList());
        }
        return showcaseDTOS;
    }

    @Operation(
            summary = "Получение витрины",
            description = "Получение витрины по UUID")
    @GetMapping("/{uuid}")
    public ShowcaseDTO getOneByUuid(@PathVariable("uuid") String uuid){
        Showcase showcase = this.showcaseService.findByUUID(UUID.fromString(uuid));
        return modelMapper.map(showcase, ShowcaseDTO.class);
    }

    @Operation(
            summary = "Создание витрины",
            description = "Создание витрины по средствам тела запроса (см. ShowcaseNewDTO)")
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ShowcaseNewDTO showcaseNewDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();

            bindingResult.getFieldErrors()
                    .forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()).append("; "));

            throw new ShowcaseNotCreatedException(stringBuilder.toString());
        }

        Showcase showcase = modelMapper.map(showcaseNewDTO, Showcase.class);
        this.showcaseService.save(showcase);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление витрины",
            description = "Обновление витрины по средствам тела запроса (см. ShowcaseUpdateDTO)")
    @PatchMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable String uuid, @RequestBody @Valid ShowcaseUpdateDTO showcaseUpdateDTO, BindingResult bindingResult){
        updateShowcaseValidator.validate(showcaseUpdateDTO, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getFieldErrors().forEach(e -> stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()).append("; "));

            throw new ShowcaseNotUpdatedException(stringBuilder.toString());
        }

        Showcase updatedShowcase = this.modelMapper.map(showcaseUpdateDTO, Showcase.class);
        this.showcaseService.update(UUID.fromString(uuid), updatedShowcase);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление витрины",
            description = "Удаление витрины по UUID (каскадирование - ON DELETE SET NULL)." +
                    "Данный метод каскадирования был выбран, если продукты мы захотим в дальнейшем 'перенести' на другие витрины")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String uuid){
        this.showcaseService.delete(UUID.fromString(uuid));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ShowcaseNotCreatedException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ShowcaseNotFoundException e){
        MyErrorResponse response = new MyErrorResponse("showcase not found, check uuid");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ShowcaseNotUpdatedException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MyErrorResponse> handleException(ShowcaseFilterException e){
        MyErrorResponse response = new MyErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
