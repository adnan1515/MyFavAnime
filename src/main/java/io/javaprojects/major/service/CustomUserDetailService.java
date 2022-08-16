package io.javaprojects.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javaprojects.major.model.CustomUserDetail;
import io.javaprojects.major.model.User;
import io.javaprojects.major.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return user.map(CustomUserDetail::new).get();
	}

}
