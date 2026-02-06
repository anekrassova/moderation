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

        PriorityLevel priority = repository.findById(category)
                .map(Category::getPriority)
                .orElse(PriorityLevel.LOW);

        cacheService.set(
                key,
                priority.name(),
                3600
        );

        return priority;
    }
}
