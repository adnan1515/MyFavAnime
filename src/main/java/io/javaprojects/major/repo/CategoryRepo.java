package io.javaprojects.major.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javaprojects.major.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
