package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany
    private List<Category> childCategories;

    @ManyToOne
    private Category parentCategory;

    @ManyToMany
    private List<Product> products;

    @Column(name = "parent_category_flag")
    private boolean parentCategoryFlag;
}
