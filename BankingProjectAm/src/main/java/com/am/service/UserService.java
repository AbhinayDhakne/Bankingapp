package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.am.model.User;
import com.am.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	public List<User> listAll() {
		return repo.findAll();
	}

	public void save(User std) {
		repo.save(std);
	}

	public User get(int id) {
		return repo.findById(id).get();
	}

	public void delete(int id) {
		repo.deleteById(id);
	}
}
