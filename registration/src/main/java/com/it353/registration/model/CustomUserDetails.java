package com.it353.registration.model;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.it353.registration.entity.Users;

public class CustomUserDetails implements UserDetails {
	
	private Users user;
	
	public CustomUserDetails(Users user) {
		this.user= user;
		}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
	}

	@Override
	public String getPassword() {
	// TODO Auto-generated method stub
	return user.getPassword();
	}

	@Override
	public String getUsername() {
	// TODO Auto-generated method stub
	return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
	}

	@Override
	public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
	}

	public String getFullName() {
	return user.getFirstName() + " " + user.getLastName();
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails user2 = (CustomUserDetails) o;
        return Objects.equals(user.getEmail(), user2.getUsername());
    }

	 @Override
	    public int hashCode() {
	        return Objects.hash(user.getEmail());
	    }

	}






