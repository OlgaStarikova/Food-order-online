package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CategoryDto;
import com.service.foodorderonline.dto.CategoryWithDishesDto;
import com.service.foodorderonline.dto.CreateCategoryRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishWithSizesDto;
import com.service.foodorderonline.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/admin/categories")
    @Operation(summary = "Create a new  category", description = "Create a new  category. "
            + "Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/categories/{id}")
    @Operation(summary = "Delete the category", description = "Delete the  category by Id."
            + "Params: id = Id of the category. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/admin/categories/{id}")
    @Operation(summary = "Update the category", description = "Update the  category by Id."
            + "Params: id = Id of the category. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping("/categories/{id}")
    @Operation(summary = "Get the  category by Id", description = "Get the  category by Id"
            + "Params: id = Id of the category. Available for all.")
    public CategoryWithDishesDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryWithDishesById(id);
    }

    @GetMapping("/categories/{id}/disheslist")
    @Operation(summary = "Get dishes with sizes and prices in the category",
            description = "Get dishes with sizes and prices in the category"
            + " by Id of category. "
            + "Params: id = Id of the category. Available for all.")
    List<DishWithSizesDto> getDishesListByCategoryId(@PathVariable Long id) {
        return categoryService.findDihesByCategoryId(id);
    }

    @GetMapping("/categories/{id}/constructor")
    @Operation(summary = "Get constructor in the category",
            description = "Get dishes in the category"
            + " by Id of category. "
            + "Params: id = Id of the category. Available for all.")
    List<DishNiceDto> getConstructorDishesByCategoryId(@PathVariable Long id) {
        return categoryService.findConstructorDishesByCategoryId(id);
    }

    @GetMapping("/categories/{id}/dishes")
    @Operation(summary = "Get dishes in the category", description = "Get dishes in the category"
            + " by Id of category. "
            + "Params: id = Id of the category. Available for all.")
    List<DishDto> getDishesByCategoryId(@PathVariable Long id) {
        return categoryService.findsByCategoryId(id);
    }
}
