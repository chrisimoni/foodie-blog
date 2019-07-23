package com.springboot.app.foodie.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.foodie.models.Category;
//import com.springboot.app.foodie.models.Category;
import com.springboot.app.foodie.models.Post;
import com.springboot.app.foodie.models.data.CategoryService;
import com.springboot.app.foodie.models.data.PostDao;



@Controller
@RequestMapping("/admin")
public class UserController {
	
	//Save the uploaded file to this folder
    private static String uploadedFolder = "/src/main/resources/static/uploads/";

    private static Path absolutePath  = Paths.get(".");
    

	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CategoryService service;
	//private CategoryDao categoryDao;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("posts", postDao.findAll());
		
		return "admin/index";
	}
	
	@GetMapping("/add_post")
	public String showForm(Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("categories", service.getAllCategories());
		return "admin/add-post";
	}
	
	@PostMapping("/add_post")
	public String addPost(@ModelAttribute Post post, @RequestParam int category, @RequestParam MultipartFile  imageFile ) throws Exception {		
		if(!imageFile.isEmpty()) {			
			long timeStamp = new Date().getTime();
			byte[] bytes = imageFile.getBytes();
			String filename = timeStamp + "_" +imageFile.getOriginalFilename();
			Path path = Paths.get(absolutePath + uploadedFolder + filename);
			Files.write(path, bytes);
			post.setImgUrl(filename);	
		}
		
		Category cat = service.getCategory(category);
		post.setCategory(cat);
		postDao.save(post);
		
		
		return "redirect:";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdatePage(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("post", postDao.findById(id));
		model.addAttribute("categories", service.getAllCategories());
		return "admin/update-post";

	}
	
	@PostMapping("/update_post")
	public String updatePost(@ModelAttribute Post post, @RequestParam int category, @RequestParam MultipartFile  imageFile ) throws Exception {		
		if(!imageFile.isEmpty()) {
			//Getting the old post image			
			String currentImage = post.getImgUrl();

			//Removing the old image from the local directory/path
			if(!currentImage.isEmpty()){
				Files.delete(Paths.get(absolutePath + uploadedFolder + currentImage));
			}
			
			//Current time in milli seconds
			long timeStamp = new Date().getTime();			
			byte[] bytes = imageFile.getBytes();
			
			//Image name to include timestamp
			String filename = timeStamp + "_" +imageFile.getOriginalFilename();
			Path path = Paths.get(absolutePath + uploadedFolder + filename);
			Files.write(path, bytes);
			
			//Setting new Image name
			post.setImgUrl(filename);
			
		}
		
		Category cat = service.getCategory(category);
		post.setCategory(cat);
		postDao.save(post);
		
		
		return "redirect:";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") int id, Model model) throws IOException {
		Post post = postDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
		
		String image = post.getImgUrl();
		
		//Removing the old image from the local directory/path
		if(!image.isEmpty() || image != null){
			Files.delete(Paths.get(absolutePath + uploadedFolder + image));
		}
		
		postDao.delete(post);		
		
		
		return this.index(model);
	}
	
	
	
	
}
