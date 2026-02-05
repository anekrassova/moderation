package org.example.service2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "category_name")
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityLevel priority;
}
