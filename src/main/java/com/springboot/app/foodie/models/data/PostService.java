package com.springboot.app.foodie.models.data;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.foodie.models.Post;

@Service
@Transactional
public class PostService {
	
	@Autowired
	private PostRepository postRepo;

	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}

	public void savePost(Post post) {
		postRepo.saveAndFlush(post);
		
	}

	public Optional<Post> findPostById(int id) {
		return postRepo.findById(id);
	}

	public void deletePost(Post post) {
		postRepo.delete(post);
		
	}

	public List<Post> findPostByCategoryId(int id) {
		return postRepo.findByCategoryId(id);
	}

}
