package com.springboot.app.foodie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.app.foodie.models.Post;
import com.springboot.app.foodie.models.data.PostDao;


@Controller
public class HomeController {
	
	@Autowired
	private PostDao postDao;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postDao.findAll());
		
		return "index";
	}
	
	
	@GetMapping("/view_post/{id}")
	public String viewPost(@PathVariable("id") int id, Model model) {
		Post getPost = postDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
		
		model.addAttribute("post", getPost);
		
		return "view-post";

	}
	
	
}
