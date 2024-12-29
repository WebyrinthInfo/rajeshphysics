package com.rajeshphysics.Controllers;

import java.time.LocalDateTime;
import java.util.List;

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

import com.rajeshphysics.Models.Quiz;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Services.QuizService;


@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("*")
public class QuizController {
	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	@Autowired
	private QuizService quizServe;
	
//----------------------{ add quiz by course - pass course id }---------------------------
	@PostMapping("/save")
	public ResponseEntity<GenericResponse<Quiz>> addCategory(@RequestBody Quiz quiz,@RequestParam ("courseId") Integer courseId, GenericResponse<Quiz> response){
		logger.info("Adding Quiz with Couse id : {} : {}",courseId, LocalDateTime.now());
		Quiz quiz1 = null;
		if(quiz != null) {
			quiz1 = quizServe.addQuiz(quiz, courseId);
			if(quiz1 != null && courseId != null) {
				response.setData(quiz1);
				response.setStatus("SUCCESS");
				response.setMsg("Data saved successfully!");
				return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.OK);
			}else {
				response.setData(quiz1);
				response.setStatus("FAILURE");
				response.setMsg("Something went wrong!");
				return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.setData(quiz1);
			response.setStatus("INVALID PARAMETER");
			response.setMsg("Invalid parameter!!");
			return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	----------------{   get All Quizess }---------------------------------------
	@GetMapping("/getAll")
	public ResponseEntity<GenericResponse<List<Quiz>>> getAllQuizes(GenericResponse<List<Quiz>> response){
			 List<Quiz> quizzess = quizServe.getQuizzess();
			if(quizzess != null) {
				response.setData(quizzess);
				response.setStatus("SUCCESS");
				response.setMsg("Data saved successfully!");
				return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.OK);
			}else {
				response.setData(quizzess);
				response.setStatus("FAILURE");
				response.setMsg("Something went wrong!");
				return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
			
//		-------------------------{ get single quiz by id }--------------------------
			@GetMapping("/get/{id}")
			public ResponseEntity<GenericResponse<Quiz>> getQuiz(@PathVariable("id") Long id, GenericResponse<Quiz> response) {
				Quiz quiz = null;
				if(id != null) {
					quiz = quizServe.getQuiz(id);
					if(quiz != null) {
						response.setData(quiz);
						response.setStatus("SUCCESS");
						response.setMsg("Data Fetched successfully!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.OK);
					}else {
						response.setData(quiz);
						response.setStatus("FAILURE");
						response.setMsg("Something went wrong!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}else {
					response.setData(quiz);
					response.setStatus("INVALID PARAMETER");
					response.setMsg("Invalid parameter!!");
					return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.BAD_REQUEST);
				}
			}
	
			//---------------{ delete Quiz by id }---------------------------
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<GenericResponse<?>> deleteQuiz(@PathVariable("id") Long  id,GenericResponse<?> response ) {
				boolean deleteQuiz = false;
				if(id != null) {
					deleteQuiz = quizServe.deleteQuiz(id);
					if(deleteQuiz == true) {
						response.setData(null);
						response.setStatus("SUCCESS");
						response.setMsg("Data deleted successfully!");
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
			
//			-------------------{ update quiz with course id  }---------------------------------
			@PutMapping("/update")
			public ResponseEntity<GenericResponse<Quiz>> updateQuiz(@RequestBody Quiz quiz,@RequestParam (value ="courseId", required = true) Long courseId, GenericResponse<Quiz> response){
				Quiz quiz1 = null;
				if(quiz != null) {
					quiz1 = quizServe.addQuiz(quiz, courseId);
					if(quiz1 != null && courseId != null) {
						response.setData(quiz1);
						response.setStatus("SUCCESS");
						response.setMsg("Data saved successfully!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.OK);
					}else {
						response.setData(quiz1);
						response.setStatus("FAILURE");
						response.setMsg("Something went wrong!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}else {
					response.setData(quiz1);
					response.setStatus("INVALID PARAMETER");
					response.setMsg("Invalid parameter!!");
					return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.BAD_REQUEST);
				}
				
			}
			
			
//			------------{ get quizzes by course }-------------------------------
			@GetMapping("/getAll/{id}")
			public ResponseEntity<GenericResponse<List<Quiz>>> getQuizzesByCourse(@PathVariable("courseId") Long courseId, GenericResponse<List<Quiz>> response) {
				List<Quiz> quizzes = null;
				if(courseId != null) {
					quizzes = quizServe.getQuizzesOfCourse(courseId);
					if(quizzes != null) {
						response.setData(quizzes);
						response.setStatus("SUCCESS");
						response.setMsg("Data Fetched successfully!");
						return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.OK);
					}else {
						response.setData(quizzes);
						response.setStatus("FAILURE");
						response.setMsg("Something went wrong!");
						return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}else {
					response.setData(quizzes);
					response.setStatus("INVALID PARAMETER");
					response.setMsg("Invalid parameter!!");
					return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.BAD_REQUEST);
				}
			}
		
//			-----------------{ get All active quizzes }-----------------------------------
			@GetMapping("/getAll/active")
			public ResponseEntity<GenericResponse<List<Quiz>>> getActiveQuizzes(GenericResponse<List<Quiz>> response) {
				 List<Quiz> quizzes = quizServe.getActiveQuizzes();
					if(quizzes != null) {
						response.setData(quizzes);
						response.setStatus("SUCCESS");
						response.setMsg("Data Fetched successfully!");
						return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.OK);
					}else {
						response.setData(quizzes);
						response.setStatus("FAILURE");
						response.setMsg("Something went wrong!");
						return new ResponseEntity<GenericResponse<List<Quiz>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				
			}
			
			// ------------{ Get All Active Quizzes of Course }-------------------------
			@GetMapping("/getAll/actived")
			public ResponseEntity<GenericResponse<List<Quiz>>> getActiveQuizzesOfCourse(@RequestParam("courseName") String courseName) {
			    GenericResponse<List<Quiz>> response = new GenericResponse<>();

			    try {
			        List<Quiz> quizzes = quizServe.getActiveQuizzesofCourse(courseName);
			        if (quizzes != null && !quizzes.isEmpty()) {
			            response.setData(quizzes);
			            response.setStatus("SUCCESS");
			            response.setMsg("Data fetched successfully!");
			            return new ResponseEntity<>(response, HttpStatus.OK);
			        } else {
			            response.setData(quizzes);
			            response.setStatus("FAILURE");
			            response.setMsg("No active quizzes found!");
			            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			        }
			    } catch (Exception e) {
			        response.setStatus("FAILURE");
			        response.setMsg("Something went wrong!");
			        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			}

			
			
//			------------------{ publish quiz by is active }-------------------------------
			@PutMapping("/publishQuiz/{qid}/{status}")
			public ResponseEntity<GenericResponse<Quiz>> publishQuiz(@PathVariable (value = "status", required = true) Long status, @PathVariable (value ="qid", required = true) Long id, GenericResponse<Quiz> response){
				Quiz quiz1 = null;
				if(id != null && status !=null) {
					quiz1 = quizServe.publishQuiz(id, status);
					if(quiz1 != null && id != null) {
						response.setData(quiz1);
						response.setStatus("SUCCESS");
						response.setMsg("Status  changed  successfully!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.OK);
					}else {
						response.setData(quiz1);
						response.setStatus("FAILURE");
						response.setMsg("Something went wrong!");
						return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}else {
					response.setData(quiz1);
					response.setStatus("INVALID PARAMETER");
					response.setMsg("Invalid parameter!!");
					return new ResponseEntity<GenericResponse<Quiz>>(response, HttpStatus.BAD_REQUEST);
				}
				
			}
			
			
			

}
