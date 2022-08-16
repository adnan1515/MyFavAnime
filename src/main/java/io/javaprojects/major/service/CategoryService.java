package io.javaprojects.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javaprojects.major.model.Category;
import io.javaprojects.major.repo.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	CategoryRepo categoryRepo;

	public void addCategory(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	public void removeCategoryById(int id) {
		categoryRepo.deleteById(id);
	}

	public Optional<Category> fetchCategoryById(int id) {
		return categoryRepo.findById(id);
	}
}
