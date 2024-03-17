package com.SpringBoot.GharBhada.Utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBoot.GharBhada.Entity.Person;
import com.SpringBoot.GharBhada.Exception.InvalidUsernameOrPassword;
import com.SpringBoot.GharBhada.Exception.ResourceNotFoundException;
import com.SpringBoot.GharBhada.Repository.PersonRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private PersonRepo personRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

			Optional<Person> person = personRepo.findByEmail(username);
//					.orElseThrow(() -> new InvalidUsernameOrPassword());
			if(person.isEmpty()) {
				throw new InvalidUsernameOrPassword();
			}
			
		CustomUserDetails customUserDetails = new CustomUserDetails(person.get());
			customUserDetails.getAuthorities().forEach(role -> {
				System.out.println(role);
			});
			return customUserDetails;
		

	}

}
