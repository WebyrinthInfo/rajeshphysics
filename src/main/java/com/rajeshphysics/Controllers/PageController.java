package com.rajeshphysics.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
public class PageController {
    
    @Value("${website.launching.date}")
    private String launchDate;
    
    // Open coming soon page
    @GetMapping("/countdown")
    public ModelAndView openCountDownPage(ModelAndView modelAndView) {
        modelAndView.setViewName("comingsoon");
        modelAndView.addObject("launchDate", launchDate); // Pass launch date to JSP
        return modelAndView;
    }
    
    @GetMapping("/")
    public ModelAndView openIndexPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("index");
    	return modelAndView;
    }
    
    @GetMapping("/home")
    public ModelAndView openHomePage(ModelAndView modelAndView) {
    	modelAndView.setViewName("index");
    	return modelAndView;
    }
    
    @GetMapping("/about")
    public ModelAndView openAboutPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("about");
    	return modelAndView;
    }
    
    @GetMapping("/login")
    public ModelAndView openLoginPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("login");
    	return modelAndView;
    }
    
    @GetMapping("/register")
    public ModelAndView openRegistorPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("register");
    	return modelAndView;
    }
    
    @GetMapping("/dashboard")
    public ModelAndView openDashboardPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("dashboard");
    	return modelAndView;
    }
    @GetMapping("/contact")
    public ModelAndView openContactUsPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("contact");
    	return modelAndView;
    }
    
    @GetMapping("/course")
    public ModelAndView openCoursePage(ModelAndView modelAndView) {
    	modelAndView.setViewName("course");
    	return modelAndView;
    }
    
    @GetMapping("/forget-password")
    public ModelAndView openForgetPasswordPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("forget-password");
    	return modelAndView;
    }
    
    @GetMapping("/add-course")
    public ModelAndView openAddCoursePage(ModelAndView modelAndView) {
    	modelAndView.setViewName("addCourseMain");
    	return modelAndView;
    }
    @GetMapping("/get-AllCourses")
    public ModelAndView openGetAllCoursesPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("allCoursesMain");
    	return modelAndView;
    }
    
    @GetMapping("/update-token")
    public ModelAndView openUpdateTokenPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("updateToken");
    	return modelAndView;
    }
    
    
    @GetMapping("/add-quiz")
    public ModelAndView openAddQuizPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("addQuizMain");
    	return modelAndView;
    }
    
    @GetMapping("/add-question")
    public ModelAndView openAddQuestionPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("addQuestionMain");
    	return modelAndView;
    }
    
    @GetMapping("/get-allQuiz")
    public ModelAndView openAllQuizzesPage(ModelAndView modelAndView) {
    	modelAndView.setViewName("allQuizzesMain");
    	return modelAndView;
    }
    
    @GetMapping("/all-questions")
    public ModelAndView openGetAllQuestionPage(@RequestParam ("id") Integer id ,ModelAndView modelAndView) {
    	modelAndView.setViewName("allQuestionsMain");
    	return modelAndView;
    }
    @GetMapping("/all-test")
    public ModelAndView openAllTPaestge(ModelAndView modelAndView) {
    	modelAndView.setViewName("allTestPaperMain");
    	return modelAndView;
    }
}
