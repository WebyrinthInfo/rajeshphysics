package com.rajeshphysics.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="QUESTIONS")
public class Question implements Serializable{
	
	private static final long serialVersionUID = -1811165658596255056L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="QUESTION_ID")
	private long id;
	
	@Column(length = 100000000, name = "QUESTION_CONTENT", nullable =  false)
	private String content;
	
	
	@Column(name="OPTION1", nullable =  false)
	private String option1;
	
	@Column(name="OPTION2", nullable =  false)
	private String option2;
	
	@Column(name="OPTION3", nullable =  false)
	private String option3;
	
	@Column(name="OPTION4", nullable =  false)
	private String option4;
	
	@Column(name="CORRECT_ANSWER", nullable =  false)
	private String correctAnswer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	@Transient
	private String givenAnswers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getGivenAnswers() {
		return givenAnswers;
	}

	public void setGivenAnswers(String givenAnswers) {
		this.givenAnswers = givenAnswers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Question() {
		super();
	}
	
	
	
}
