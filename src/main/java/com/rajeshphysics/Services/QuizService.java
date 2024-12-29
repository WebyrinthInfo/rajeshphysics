package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Models.Quiz;



public interface QuizService {
	public Quiz addQuiz(Quiz quiz, long id);
	public Quiz updateQuiz(Quiz quiz, Long id);
	public List<Quiz> getQuizzess();
	public Quiz getQuiz(long id);
	public boolean deleteQuiz(long id);
	public List<Quiz> getActiveQuizzes();
	public Quiz publishQuiz(Long id, Long status);
	public List<Quiz> getQuizzesOfCourse(Long courseId);
	public List<Quiz> getActiveQuizzesofCourse(String  courseName);
}
