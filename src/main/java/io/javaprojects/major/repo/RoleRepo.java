package io.javaprojects.major.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javaprojects.major.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
