package com.ecommerce.service;

import com.ecommerce.dto.categories.ChildCategoryCreationRequest;
import com.ecommerce.dto.categories.ParentCategoryCreationRequest;
import com.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllByParentCategoryTrue();

    Optional<Category> findByName(String name);

    List<Category> findAllByParentCategoryFalse();

    Optional<Category> findById(Long id);

    Category save(Category category);

    List<Category> saveAll(List<Category> categories);

    void delete(Category category);

    Category createParentCategory(ParentCategoryCreationRequest parentCategoryCreationRequest);

    Category createChildCategory(ChildCategoryCreationRequest childCategoryCreationRequest);

    List<Category> getAllCategories();


}
