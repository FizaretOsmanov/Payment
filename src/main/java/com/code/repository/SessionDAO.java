package com.code.repository;

import com.code.model.CurrentSessionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDAO extends JpaRepository<CurrentSessionUser, Integer> {

	Optional<CurrentSessionUser> findByUserId(Integer userId);

	Optional<CurrentSessionUser> findByUuid(String uuid);


}
