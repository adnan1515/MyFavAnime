package io.javaprojects.major.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javaprojects.major.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	public List<Product> findAllByCategory_Id(int id);
}
