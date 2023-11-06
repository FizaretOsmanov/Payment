package com.code.repository;

import com.code.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByUserName(String userName);

	Optional<Customer> findByMobileNo(String mobileNo);
}
