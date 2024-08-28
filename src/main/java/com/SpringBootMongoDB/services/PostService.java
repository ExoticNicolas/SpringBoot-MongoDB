package com.SpringBootMongoDB.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootMongoDB.domain.Post;
import com.SpringBootMongoDB.repositories.PostRepository;
import com.SpringBootMongoDB.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		try {
		Optional<Post> post = postRepository.findById(id);
		return post.get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(id);
		}
		
	}
	
	public List<Post> findByTitle(String text){
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
	
}
