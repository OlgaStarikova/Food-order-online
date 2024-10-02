package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.service.IngredService;
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
@Tag(name = "Ingred management", description = "Endpoints for managing ingredients")
@RequestMapping("/ingreds")
@RequiredArgsConstructor
public class IngredController {
    private final IngredService ingredService;

    @PostMapping
    @Operation(summary = "Create a new ingredient", description = "Create a new ingredient. "
            + "Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IngredDto createIngred(@RequestBody @Valid CreateIngredRequestDto requestDto) {
        return ingredService.save(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get a list of ingredients", description = "Get all ingredients."
            + "Params(optional): page = page number, size = count of ingredients in one page,"
            + " namefield = field for sorting. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<IngredDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return ingredService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the ingred by Id", description = "Get the ingred by Id"
            + "Params: id = Id of the ingred. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IngredDto getIngredById(@PathVariable Long id) {
        return ingredService.getById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the ingred", description = "Delete the ingred by Id."
            + "Params: id = Id of the ingred. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteIngred(@PathVariable Long id) {
        ingredService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the ingred", description = "Update the ingred by Id."
            + "Params: id = Id of the ingredient. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IngredDto updateIngred(@PathVariable Long id,
                                  @RequestBody @Valid CreateIngredRequestDto requestDto) {
        return ingredService.update(id, requestDto);
    }
}
