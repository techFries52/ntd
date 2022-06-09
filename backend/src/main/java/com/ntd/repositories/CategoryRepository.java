package com.ntd.repositories;

import com.ntd.models.Category;
import com.ntd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
