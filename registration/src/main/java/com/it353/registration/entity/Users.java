package com.it353.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users") //match with the users table in the database
public class Users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true, length = 45)
private String email;

@Column(nullable = false, length = 64)
private String password;

@Column(name = "first_name", nullable = false, length = 20)
private String firstName;

@Column(name = "last_name", nullable = false, length = 20)
private String lastName;

@Column(name = "photo", nullable = false, length = 64)
private String photo;


public String getPhoto() {
	return photo;
}

public void setPhoto(String photo) {
	this.photo = photo;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhotosImagePath() {//path to get the image
    if (photo == null || id == null) return null;    
    return "/images/"+id + "/" + photo;
}

}

