package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dish management", description = "Endpoints for managing dishes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    @GetMapping
    @Operation(summary = "Get a list of dishes", description = "Get a list of all available dishes."
            + "Params(optional): page = page number, size = count of dishes in one page,"
            + " namefield = field for sorting. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public List<DishDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return dishService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the dish by Id", description = "Get the dish by Id"
            + "Params: id = Id of the dish. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public DishDto getDishById(@PathVariable Long id) {
        return dishService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new dish", description = "Create a new dish. "
            + "Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DishDto createDish(@RequestBody @Valid CreateDishRequestDto requestDto) {
        return dishService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the dish", description = "Delete the dish by Id."
            + "Params: id = Id of the dish. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDishById(@PathVariable Long id) {
        dishService.deleteDish(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the dish", description = "Update the dish by Id."
            + "Params: id = Id of the dish. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DishDto updateDish(@PathVariable Long id,
                              @RequestBody @Valid CreateDishRequestDto requestDto) {
        return dishService.updateDish(id, requestDto);
    }
}
