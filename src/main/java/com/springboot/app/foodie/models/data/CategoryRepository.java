package com.springboot.app.foodie.models.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.foodie.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCategory(String name);

}
