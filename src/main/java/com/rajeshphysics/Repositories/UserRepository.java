package com.rajeshphysics.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajeshphysics.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMobile(String string);

	@Query(value = "SELECT * FROM rajeshphysics.users WHERE id LIKE CONCAT('%', :search, '%') OR address LIKE CONCAT('%', :search, '%') OR mobile LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<User> findByKeyword(@Param("search") String search, PageRequest page);
	

}
