package com.springboot.app.foodie.models.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.foodie.models.Post;

@Repository
@Transactional
public interface PostDao extends CrudRepository<Post, Integer> {

}
