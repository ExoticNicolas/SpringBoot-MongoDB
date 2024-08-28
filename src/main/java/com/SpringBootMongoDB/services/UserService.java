package com.SpringBootMongoDB.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootMongoDB.domain.User;
import com.SpringBootMongoDB.dto.UserDTO;
import com.SpringBootMongoDB.repositories.UserRepository;
import com.SpringBootMongoDB.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User insert(User user) {
		user = userRepository.save(user);
		return user;

	}

	public User findById(String id) {
		try {
			Optional<User> user = userRepository.findById(id);
			return user.get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	public  User fromDTO(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		return user;
	}
	

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User updateUser = findById(user.getId());
		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		userRepository.save(updateUser);
		return updateUser;
	}
}
