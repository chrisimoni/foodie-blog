package com.springboot.app.foodie.models.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.foodie.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByCategoryId(int id);

}
