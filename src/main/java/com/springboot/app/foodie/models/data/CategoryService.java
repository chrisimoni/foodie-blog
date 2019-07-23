package com.springboot.app.foodie.models.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.foodie.models.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public List<Category> getAllCategories() {
		return repo.findAll();
	}
	
	public Category getCategory(int id) {
        return repo.findById(id).get();
    }
	
}
