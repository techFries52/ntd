package com.ntd.services;

import com.ntd.models.Category;
import com.ntd.models.Item;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    boolean ifCategoryExists(int categoryId);

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Category category);

    Category getCategoryById(int categoryId);

    List<Category> getAllCategories();

    Set<Category> getAllCategoriesByUser(int user_id);

}
