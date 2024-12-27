package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Models.Question;



public interface QuestionService {
	public Question addQuestion(Question question, Long id);
	public Question updateQuestion(Question question);
	public List<Question> getQuestions();
	public Question getQuestion(Long id);
	public boolean deleteQuestion(Long id);
	public List<Question> getQuestionsOfQuiz(long id);
	public Question get(Long questionId);
	public List<Question> getSublistedQuestionByQuiz(long id);
}
