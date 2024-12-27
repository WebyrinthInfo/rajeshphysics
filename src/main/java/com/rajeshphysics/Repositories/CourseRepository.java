package com.rajeshphysics.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByName(String name);

	@Query(value = "SELECT * FROM rajeshphysics.courses WHERE id LIKE CONCAT('%', :search, '%') OR course_name LIKE CONCAT('%', :search, '%') OR course_language LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<Course> findByKeyword(@Param("search") String search, PageRequest page);

}
