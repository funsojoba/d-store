package com.codewithfj.store.Entity;

import com.codewithfj.store.Dto.CategoryResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="category")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
