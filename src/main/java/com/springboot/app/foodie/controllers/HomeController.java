package com.springboot.app.foodie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.app.foodie.models.Category;
import com.springboot.app.foodie.models.Post;
import com.springboot.app.foodie.models.data.CategoryService;
import com.springboot.app.foodie.models.data.PostService;



@Controller
public class HomeController {
	
	@Autowired
	private PostService postDao;
	
	@Autowired
	private CategoryService category;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postDao.getAllPosts());
		
		return "index";
	}
	
	
	@GetMapping("/view_post/{id}")
	public String viewPost(@PathVariable("id") int id, Model model) {
		Post getPost = postDao.findPostById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
		
		model.addAttribute("post", getPost);
		
		return "view-post";

	}
	
	@GetMapping("/category/{name}")
	public String getPostByCategory(Model model, @PathVariable("name") String name) {
		Category cat = category.getCategoryByName(name);
		List<Post> getPosts = postDao.findPostByCategoryId(cat.getId());
		//System.out.println(posts);
		model.addAttribute("posts", getPosts);
		return "category";
	}
	
	
}
