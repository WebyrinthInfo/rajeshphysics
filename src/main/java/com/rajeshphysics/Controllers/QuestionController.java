package com.rajeshphysics.Controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Models.Question;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Services.QuestionService;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionService questionServe;
	
	//---------------------{ create Question by quiz id }---------------------------------------
	@PostMapping("/save/{id}")
	public ResponseEntity<GenericResponse<Question>> saveQuesiton(@PathVariable("id")Long id, GenericResponse<Question> response,
			@RequestBody Question question ) {
		Question addQuestion = null;
		if (question != null && id !=null ) {
			addQuestion = questionServe.addQuestion(question, id);
			if (addQuestion != null) {

				response.setData(addQuestion);
				response.setStatus("SUCCESS");
				response.setMsg("Question save successflly!");
				logger.info("Question save successflly!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.OK);
			} else {
				response.setStatus("FAILURE");
				response.setData(addQuestion);
				response.setMsg("Someting went wrong !!");
				logger.info("Someting went wrong !!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setStatus("INVALID PARAMETER");
			response.setMsg("Invalid Input !!");
			logger.info("Invalid Input !!");
			return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
		
		
//-------------------{ update Question }----------------------------------------- 
	@PutMapping("/update")
	public ResponseEntity<GenericResponse<Question>> updateQuestion(GenericResponse<Question> response,
			@RequestBody Question question ) {
		Question updateQuestion = null;
		if (question != null ) {
			updateQuestion = questionServe.updateQuestion(question);
			if (updateQuestion != null) {

				response.setData(updateQuestion);
				response.setStatus("SUCCESS");
				response.setMsg("Question save successflly!");
				logger.info("Question save successflly!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.OK);
			} else {
				response.setStatus("FAILURE");
				response.setData(updateQuestion);
				response.setMsg("Someting went wrong !!");
				logger.info("Someting went wrong !!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setStatus("INVALID PARAMETER");
			response.setMsg("Invalid Input !!");
			logger.info("Invalid Input !!");
			return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.BAD_REQUEST);
		}
		
	}	
	
	
	
//	------------------{ get single question  by question id }---------------------------------
	@PostMapping("/get/{id}")
	public ResponseEntity<GenericResponse<Question>> getSingleQuesiton(GenericResponse<Question> response,
			@PathVariable ("id") Long id ) {
		Question question = null;
		if (id != null ) {
			question = questionServe.getQuestion(id);
			if (question != null) {
				response.setData(question);
				response.setStatus("SUCCESS");
				response.setMsg("Question fetched successflly!");
				logger.info("Question fetched successflly!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.OK);
			} else {
				response.setStatus("FAILURE");
				response.setData(question);
				response.setMsg("Someting went wrong !!");
				logger.info("Someting went wrong !!");
				return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setStatus("INVALID PARAMETER");
			response.setMsg("Invalid Input !!");
			logger.info("Invalid Input !!");
			return new ResponseEntity<GenericResponse<Question>>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	---------{ get sublist questions by quiz id }-----------------------------------
	@GetMapping("/getAll/sublisted/{id}")
	public ResponseEntity<GenericResponse<List<Question>>> getQuizzesByCategory(@PathVariable("id") Long id, GenericResponse<List<Question>> response) {
		List<Question> questions = null;
		if(id != null) {
			 questions = questionServe.getSublistedQuestionByQuiz(id);
			if(questions != null) {
				
				response.setData(questions);
				response.setStatus("SUCCESS");
				response.setMsg("Data Fetched successfully!");
				return new ResponseEntity<GenericResponse<List<Question>>>(response, HttpStatus.OK);
			}else {
				response.setData(questions);
				response.setStatus("FAILURE");
				response.setMsg("Something went wrong!");
				return new ResponseEntity<GenericResponse<List<Question>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.setData(questions);
			response.setStatus("INVALID PARAMETER");
			response.setMsg("Invalid parameter!!");
			return new ResponseEntity<GenericResponse<List<Question>>>(response, HttpStatus.BAD_REQUEST);
		}
	}
		
		
		


		
//-------------------{ delete single Question }---------------------------------
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<GenericResponse<?>> deleteSingleQuestion(@PathVariable("id") Long  id,GenericResponse<?> response ) {
			boolean deleteQuesiton = false;
			if(id != null) {
				deleteQuesiton = questionServe.deleteQuestion(id);
				if(deleteQuesiton == true) {
					response.setData(null);
					response.setStatus("SUCCESS");
					response.setMsg("Question deleted successfully!");
					return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
				}else {
					response.setData(null);
					response.setStatus("FAILURE");
					response.setMsg("Something went wrong!");
					return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {
				response.setData(null);
				response.setStatus("INVALID PARAMETER");
				response.setMsg("Invalid parameter!!");
				return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		
//--------------------{  get All Questions by Quiz id for CMS }---------------------------------------
		@GetMapping("/getAll/{id}")
		public ResponseEntity<GenericResponse<List<Question>>> getQuestionsByQuizCMS(
				@PathVariable("id") long id,
				GenericResponse<List<Question>> response) {
			List<Question> questions = questionServe.getQuestionsOfQuiz(id);
			if (questions != null) {
				response.setData(questions);
				response.setStatus("SUCCESS");
				response.setMsg("Fetch Successfully !!");
			} else {
				response.setData(questions);
				response.setStatus("SUCCESS");
				response.setMsg("Data not Available!");
			}
			return new ResponseEntity<GenericResponse<List<Question>>>(response, HttpStatus.OK);
		}
		
		
		//eval-quiz
		@PostMapping("/evel-quiz")
		public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions, Principal principal) throws Exception{
			Integer totalQuestions = 0;
			Integer correctQuestions= 0;
			Integer wrongQuestions = 0;
			Double percentage = 0.0;
			Integer Attemped = 0;
			String Grade = "";
			
			//wrong question added in wql
			List<Object> wql = new ArrayList<>();
			
			
			for (Question q : questions) {
				totalQuestions +=1;
				Question question = questionServe.getQuestion(q.getId());
				if(question.getCorrectAnswer().equals(q.getGivenAnswers())) {
					//count correct answer
					correctQuestions +=1;
				}else {
					// collect wrong questions in map
				
					Map<String, Object>  collectedWrongQuestions = new HashMap<>();
					wrongQuestions +=1;
					collectedWrongQuestions.put("sno.",wrongQuestions);
					collectedWrongQuestions.put("Ques", question.getContent());
					collectedWrongQuestions.put("Correct Answer",question.getCorrectAnswer());
					if(q.getGivenAnswers() == null) {
						collectedWrongQuestions.put("Selected Answer", "Not choose");
					}else {
						collectedWrongQuestions.put("Selected Answer", q.getGivenAnswers());
					}
					
					wql.add(collectedWrongQuestions);
				}
				
				//count no of attempt question
				if(q.getGivenAnswers()!=null) {
					Attemped+=1;
				}
				
			}
			
		
			//count percentage
			percentage = (double)(correctQuestions *100)/totalQuestions;
				
			//Grade calculation
			if(percentage >=95) {
				Grade+="Excellent : 👑 😍 🎓 ";
			}else if(percentage <=96 && percentage>=81) {
				Grade+="Very Good : 😄 👌 ";
			}else if(percentage <=80 && percentage>=71) {
				Grade+="Good : 😏 👍 ";
			}else if(percentage <=70 && percentage>=61) {
				Grade+="Poor : 😟 ";
			}else if(percentage <=60 && percentage>=51) {
				Grade+="very Poor  : 😨 😩 ";
			}else {
				Grade+="Fail : 😭👎 " ;
			}
			
			Date date = new Date();
			//collect all info in map
			Map<String, Object>  collectedcAllInfo= new HashMap<>();
			collectedcAllInfo.put("submitedTime", date);
			collectedcAllInfo.put("totalQuestions", totalQuestions);
			collectedcAllInfo.put("correctQuestions", correctQuestions);
			collectedcAllInfo.put("percentage", percentage);
			collectedcAllInfo.put("Attemped", Attemped);
			collectedcAllInfo.put("Grade", Grade);
			collectedcAllInfo.put("AllWrongQuestions", wql);
			collectedcAllInfo.put("wrongQuestions", wrongQuestions);
			

			
			
			Random rad = new Random();
			int nextInt = rad.nextInt(100000);
//			String filePath= path+"\\Record"+nextInt+".pdf";

//			String name = principal.getName();
//			User user = userRepo.findByUsername(name);
//			String email = user.getEmail();
//			pdfGenerator.generateItinerary(user, filePath, collectedcAllInfo);
//			emailUtil.sendItinerary(email, filePath);
			return ResponseEntity.ok(collectedcAllInfo);
		}
		

}
