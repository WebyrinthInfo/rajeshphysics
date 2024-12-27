package com.rajeshphysics.ServiceImpls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Exceptions.ResourceNotFoundException;
import com.rajeshphysics.Models.Question;
import com.rajeshphysics.Models.Quiz;
import com.rajeshphysics.Repositories.QuestionRepository;
import com.rajeshphysics.Repositories.QuizRepository;
import com.rajeshphysics.Services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private QuizRepository quizRepo;

	@Override
	public Question addQuestion(Question question, Long id) {
		Quiz quiz = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Quiz is not found with :"+id));
		question.setQuiz(quiz);
		Question save = questionRepo.save(question);
		return save;
	}

	@Override
	public Question updateQuestion(Question question) {
		Question save = questionRepo.save(question);
		return save;
	}

	@Override
	public List<Question> getQuestions() {
		List<Question> findAll = questionRepo.findAll();
		return findAll;
	}

	@Override
	public Question getQuestion(Long id) {
		Question question = questionRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question is not found with :"+ id));
		return question;
	}

	@Override
	public boolean deleteQuestion(Long id) {
		boolean result = false;
		try {
			questionRepo.deleteById(id);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Question> getQuestionsOfQuiz(long id) {
		Quiz quiz = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question is not found with :"+ id));
		List<Question> question =  questionRepo.findByQuiz(quiz);
		return question;
	}
	
	
	@Override
	public List<Question> getSublistedQuestionByQuiz(long id){
		Quiz quiz = quizRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question is not found with :"+ id));
		List<Question> questions =  questionRepo.findByQuiz(quiz);
		List<Question> list = new ArrayList<Question>(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestion())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion()+1));
		}
		Collections.shuffle(list);
		return list;
	}

	@Override
	public Question get(Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
