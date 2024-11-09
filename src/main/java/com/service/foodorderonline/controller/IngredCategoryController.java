package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CreateIngredCategoryRequestDto;
import com.service.foodorderonline.dto.IngredCategoryDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.service.IngredCategoryService;
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

@RestController
@Tag(name = "IngredCategory management", description = "Endpoints for managing categories")
@RequestMapping("/ingredcategories")
@RequiredArgsConstructor
public class IngredCategoryController {
    private final IngredCategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new ingred category",
            description = "Create a new ingred category. "
            + "Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IngredCategoryDto createIngredCategory(@RequestBody @Valid
                                                      CreateIngredCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get a list of ingred categories",
            description = "Get all ingred categories."
            + "Params(optional): page = page number, size = count of books in one page,"
            + " namefield = field for sorting. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public List<IngredCategoryDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the ingred category by Id",
            description = "Get the ingred category by Id"
            + "Params: id = Id of the category. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public IngredCategoryDto getIngredCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the category", description = "Delete the ingred category by Id."
            + "Params: id = Id of the category. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteIngredCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the category", description = "Update the inngred category by Id."
            + "Params: id = Id of the book. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IngredCategoryDto updateIngredCategory(@PathVariable Long id,
                                  @RequestBody @Valid CreateIngredCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping("/{id}/ingreds")
    @Operation(summary = "Get ingreds in the category", description = "Get ingreds in the category"
            + " by Id of category. "
            + "Params: id = Id of the category. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    List<IngredDto> getDishesByIngredCategoryId(@PathVariable Long id) {
        return categoryService.findIngredsByCategoryId(id);
    }
}
