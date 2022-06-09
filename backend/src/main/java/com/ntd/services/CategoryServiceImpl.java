package com.ntd.services;

import com.ntd.models.Category;
import com.ntd.models.Item;
import com.ntd.repositories.CategoryRepository;
import com.ntd.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public boolean ifCategoryExists(int categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        return category.isPresent();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        Category checkedCategory = new Category();

        if(category.isPresent()){
            checkedCategory = category.get();
        }
        return checkedCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Set<Category> getAllCategoriesByUser(int user_id) {
        return categoryRepo.findAll().stream()
                .filter( category ->  category.getUser().getUser_id() == user_id)
                .collect(Collectors.toSet());
    }
}
