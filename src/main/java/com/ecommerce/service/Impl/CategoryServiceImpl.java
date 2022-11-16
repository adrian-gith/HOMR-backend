package com.ecommerce.service.Impl;

import com.ecommerce.dto.categories.ChildCategoryCreationRequest;
import com.ecommerce.dto.categories.ParentCategoryCreationRequest;
import com.ecommerce.exception.NoCategoryWithSuchId;
import com.ecommerce.exception.NoCategoryWithSuchNameException;
import com.ecommerce.exception.NoChildCategoriesException;
import com.ecommerce.exception.NoParentCategoriesException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllByParentCategoryTrue() {
        if (categoryRepository.findAllByParentCategoryTrue() == null) {
            throw new NoParentCategoriesException();
        }
        return categoryRepository.findAllByParentCategoryTrue();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.ofNullable(categoryRepository.findByName(name).orElseThrow(NoCategoryWithSuchNameException::new));
    }

    @Override
    public List<Category> findAllByParentCategoryFalse() {
        if (categoryRepository.findAllByParentCategoryFalse() == null) {
            throw new NoChildCategoriesException();
        }
        return categoryRepository.findAllByParentCategoryFalse();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categoryRepository.findById(id).orElseThrow(NoCategoryWithSuchId::new));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category createParentCategory(ParentCategoryCreationRequest parentCategoryCreationRequest) {
        Category category = new Category();
        category.setName(parentCategoryCreationRequest.getName());
        category.setParentCategory(null);
        List<Category> childCategories = new ArrayList<>();
        category.setChildCategories(childCategories);
        category.setProducts(new ArrayList<Product>());
        category.setParentCategoryFlag(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category createChildCategory(ChildCategoryCreationRequest childCategoryCreationRequest) {
        Category category = new Category();
        category.setName(childCategoryCreationRequest.getName());
        Category parent = categoryRepository.findById(childCategoryCreationRequest.getParentCategoryId()).orElseThrow(NoCategoryWithSuchId::new);
        category.setParentCategory(parent);
        category.setParentCategoryFlag(false);
        category.setChildCategories(new ArrayList<>());
        category.setProducts(new ArrayList<>());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


}
