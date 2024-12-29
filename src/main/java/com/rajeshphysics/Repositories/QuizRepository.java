package com.rajeshphysics.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {


	List<Quiz> findByIsActive(int i);

	List<Quiz> findByCourse(Course course);

	List<Quiz> findByCourseAndIsActive(Course course, int i);

}
