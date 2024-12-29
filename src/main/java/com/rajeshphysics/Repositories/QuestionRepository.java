package com.rajeshphysics.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Question;
import com.rajeshphysics.Models.Quiz;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByQuiz(Quiz quiz);

}
