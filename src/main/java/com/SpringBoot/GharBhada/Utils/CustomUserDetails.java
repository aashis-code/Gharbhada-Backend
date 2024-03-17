package com.SpringBoot.GharBhada.Utils;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SpringBoot.GharBhada.Entity.Person;

public class CustomUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Person person;

    public CustomUserDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can customize the roles/authorities based on your Person entity
    	
    	if ("admin@gmail.com".equals(person.getEmail())) {
    		 return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    	
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can add logic based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can add logic based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can add logic based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // You can add logic based on your requirements
    }
}
