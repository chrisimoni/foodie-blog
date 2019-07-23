package com.springboot.app.foodie.models;

//import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String category;
	
	@OneToMany
	@JoinColumn(name="category_id")
	private List<Post> posts;
	
	public Category() {}

	public Category(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	
	
}
