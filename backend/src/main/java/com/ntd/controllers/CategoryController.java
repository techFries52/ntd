package com.ntd.controllers;

import com.ntd.models.Category;
import com.ntd.models.Category;
import com.ntd.services.CategoryService;
import com.ntd.services.CategoryService;
import com.ntd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

    @Autowired
    CategoryService catServ;

    @Autowired
    UserService uServ;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        ResponseEntity<List<Category>> response;
        List<Category> categories = catServ.getAllCategories();

        if (categories.size() > 0) {
            response = new ResponseEntity<>(categories, HttpStatus.OK); // 200
        } else if (categories.size() == 0) {
            response = new ResponseEntity<>(categories, HttpStatus.NO_CONTENT); // 204
        } else {
            response = new ResponseEntity<>(categories, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getSingleCategory(@PathVariable("id") int id) {
        ResponseEntity<Category> response;
        Category category;

        if (!catServ.ifCategoryExists(id)) {
            // category does not exist
            category = new Category();
            response = new ResponseEntity<>(category, HttpStatus.NOT_FOUND); // 404
        } else {
            // category does exist
            category = catServ.getCategoryById(id);
            response = new ResponseEntity<>(category, HttpStatus.OK); // 200
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Category> saveNewCategory(@RequestBody Category newCategory) {
        ResponseEntity<Category> response;

        if (catServ.ifCategoryExists(newCategory.getCategoryId())) {
            // category already exists
            response = new ResponseEntity<>(newCategory, HttpStatus.CONFLICT); // 409
        } else {
            // category does not already exist
            catServ.saveCategory(newCategory);
            response = new ResponseEntity<>(newCategory, HttpStatus.CREATED); // 201
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category updatedCategory, @PathVariable("id") int id) {
        ResponseEntity<Category> response;

        if (catServ.ifCategoryExists(updatedCategory.getCategoryId()) && updatedCategory.getCategoryId() == id) {
            // category exists
            catServ.updateCategory(updatedCategory);
            response = new ResponseEntity<>(updatedCategory, HttpStatus.OK); // 200
        } else {
            // category does not exist
            response = new ResponseEntity<>(updatedCategory, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id) {
        ResponseEntity<Category> response;
        Category category;

        if (catServ.ifCategoryExists(id)) {
            // category does exist
            category = catServ.getCategoryById(id);
            catServ.deleteCategory(category);
            response = new ResponseEntity<>(category, HttpStatus.OK); // 200
        } else {
            // category does not exist
            category = new Category();
            response = new ResponseEntity<>(category, HttpStatus.NOT_FOUND); // 404
        }
        return response;
    }

    @GetMapping("{user_id}")
    public ResponseEntity<Set<Category>> getAllCategoriesByUser(@PathVariable("user_id") int user_id) {
        ResponseEntity<Set<Category>> response;
        Set<Category> userCategories;

        if (uServ.ifUserExists(user_id)) {
            // user does exist
            userCategories = catServ.getAllCategoriesByUser(user_id);

            if (userCategories.size() > 0) {
                response = new ResponseEntity<>(userCategories, HttpStatus.OK); //200
            } else {
                response = new ResponseEntity<>(userCategories, HttpStatus.NO_CONTENT); // 204
            }

        } else {
            // user does not exist
            userCategories = new HashSet<>();
            response = new ResponseEntity<>(userCategories, HttpStatus.NOT_FOUND); // 404
        }

        return response;
    }


}