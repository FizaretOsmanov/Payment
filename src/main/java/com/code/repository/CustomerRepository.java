package com.code.repository;

import com.code.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{


	Optional<Customer> findByMobileNo(String mobileNo);

	Optional<Customer> findByUserId(Long newSignUpId);

	Optional<Customer> findByUserName(String userName);

	Boolean existsByEmail(String email);

	Customer findByEmailIgnoreCase(String emailId);
}
