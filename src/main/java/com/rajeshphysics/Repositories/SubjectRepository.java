package com.rajeshphysics.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Models.Subject;

@RestController
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	
	@Query(value = "SELECT * FROM rajeshphysics.subjects WHERE subject_name = :name", nativeQuery = true)
	Optional<Subject> findByName(@Param("name") String name);

	
	@Query(value = "SELECT * FROM rajeshphysics.subjects WHERE id LIKE CONCAT('%', :search, '%') OR subject_name LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<Subject> findByKeyword(@Param("search") String search, PageRequest page);
	


}
