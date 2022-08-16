package io.javaprojects.major.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javaprojects.major.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findUserByEmail(String email);
}
