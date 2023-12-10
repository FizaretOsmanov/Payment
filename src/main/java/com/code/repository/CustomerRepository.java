package com.code.repository;

import com.code.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByUserName(String userName);

	Optional<Customer> findByMobileNo(String mobileNo);

    Optional<Customer> findByUserId(Long newSignUpId);
}
