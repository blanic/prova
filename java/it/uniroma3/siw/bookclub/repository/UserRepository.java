package it.uniroma3.siw.bookclub.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.bookclub.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	

}