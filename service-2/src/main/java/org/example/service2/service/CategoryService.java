package org.example.service2.service;

import lombok.RequiredArgsConstructor;
import org.example.service2.entity.Category;
import org.example.service2.entity.PriorityLevel;
import org.example.service2.repo.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CacheService cacheService;
    private final CategoryRepo repository;

    public PriorityLevel getPriority(String category){
        String key = "category:priority:" + category;

        String cached = cacheService.get(key);
        if (cached != null) {
            return PriorityLevel.valueOf(cached);
        }

        Category foundCategory =
                repository.findById(category)
                        .orElseThrow(() ->
                                new RuntimeException("Unknown category"));

        cacheService.set(
                key,
                foundCategory.getPriority().name(),
                3600
        );

        return foundCategory.getPriority();
    }
}
