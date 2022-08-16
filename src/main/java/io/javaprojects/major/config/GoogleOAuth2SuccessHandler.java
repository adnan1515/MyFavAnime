package io.javaprojects.major.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import io.javaprojects.major.model.Role;
import io.javaprojects.major.model.User;
import io.javaprojects.major.repo.RoleRepo;
import io.javaprojects.major.repo.UserRepo;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;

	private RedirectStrategy redirectStratergy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if (!userRepo.findUserByEmail(email).isPresent()) {
			User user = new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);

			List<Role> roles = new ArrayList<>();
			roles.add(roleRepo.findById(2).get());
			user.setRoles(roles);

			userRepo.save(user);
		}
		redirectStratergy.sendRedirect(request, response, "/home");

	}

}
