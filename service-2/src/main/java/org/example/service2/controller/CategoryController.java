package org.example.service2.controller;

import lombok.RequiredArgsConstructor;
import org.example.service2.dto.CategoryPriorityRequest;
import org.example.service2.dto.CategoryPriorityResponse;
import org.example.service2.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrich")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/priority")
    public CategoryPriorityResponse getCategoryPriority(
            @RequestBody CategoryPriorityRequest request) {
        return new CategoryPriorityResponse(
                request.getCategory(),
                categoryService.getPriority(request.getCategory())
        );
    }
}
