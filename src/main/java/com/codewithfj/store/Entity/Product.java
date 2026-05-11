package com.codewithfj.store.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Boolean isActive;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Long rating;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
