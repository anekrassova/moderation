package org.example.service2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.service2.entity.PriorityLevel;

@Getter
@Setter
@AllArgsConstructor
public class CategoryPriorityResponse {
    private String category;
    private PriorityLevel priority;
}
