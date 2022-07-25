package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.am.model.Account;
import com.am.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repo;
	public List<Account> listAll() {
		return repo.findAll();
	}

	public void save(Account acc) {
		repo.save(acc);
	}

	public Account get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}
