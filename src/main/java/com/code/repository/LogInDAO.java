package com.code.repository;

import com.code.model.LogIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInDAO extends JpaRepository<LogIn, Integer>{
}
