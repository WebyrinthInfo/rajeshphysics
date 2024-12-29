package com.rajeshphysics.ServiceImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Exceptions.ResourceNotFoundException;
import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Models.Quiz;
import com.rajeshphysics.Repositories.CourseRepository;
import com.rajeshphysics.Repositories.QuizRepository;
import com.rajeshphysics.Services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public Quiz addQuiz(Quiz quiz, long courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course is not found with :"+ courseId));
		quiz.setCourse(course);
		Quiz save = quizRepo.save(quiz);
		return save;
	}

	
	@Override
	public Quiz updateQuiz(Quiz quiz, Long courseId) {
			Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course is not found with :"+ courseId));
			quiz.setTitle(quiz.getTitle().trim().toUpperCase());
			quiz.setCourse(course);
			Quiz save = quizRepo.save(quiz);
			return save;
	}

	@Override
	public List<Quiz> getQuizzess() {
		List<Quiz> findAll = quizRepo.findAll();
		return findAll;
	}

	@Override
	public Quiz getQuiz(long id) {
		Quiz quiz = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz is not found with :"+ id));
		return quiz;
	}

	@Override
	public boolean deleteQuiz(long id) {
		boolean result = false;
		try {
			quizRepo.deleteById(id);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public List<Quiz> getQuizzesOfCourse(Long courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course is not found with :"+ courseId));
		List<Quiz> quizzes = quizRepo.findByCourse(course);
		return quizzes;
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		List<Quiz> quizzes = quizRepo.findByIsActive(1);
		return quizzes;
	}


	@Override
	public List<Quiz> getActiveQuizzesofCourse(String courseName) {
	    // Convert courseId from Integer to Long
//	    Long longCourseId = Long.valueOf(courseId);

	    // Fetch the course by ID or throw a ResourceNotFoundException if not found
	    Course course = courseRepo.findByName(courseName)
	        .orElseThrow(() -> new ResourceNotFoundException("Course is not found with ID: " + courseName));

	    // Fetch the list of active quizzes for the course
	    List<Quiz> activeQuizzes = quizRepo.findByCourseAndIsActive(course, 1);

	    return activeQuizzes;
	}


	@Override
	public Quiz publishQuiz(Long id, Long status) {
		Quiz quiz = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz is not found with :"+ id));
		if(status==1) {
			quiz.setIsActive(1);
			quizRepo.save(quiz);
		}else {
			quiz.setIsActive(0);
			quizRepo.save(quiz);
		}
		return quiz;
	}

	

	

}
