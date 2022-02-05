package com.it353.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it353.registration.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
//add SQL


@Query("SELECT u FROM Users u WHERE u.email = ?1")

public Users findByEmail(String username);



}
