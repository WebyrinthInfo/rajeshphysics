package com.rajeshphysics.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="QUIZZES")
public class Quiz implements Serializable {
	
	private static final long serialVersionUID = -3539626408849531595L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="QUIZ_ID", nullable =  false)
	private long id;
	
	@Column(name="QUIZ_TITLE", nullable =  false)
	private String title;
	
	@Column(name="QUIZ_DESCRIPTION", nullable =  false, length = 1000000000)
	private String description;
	
	@Column(name="QUIZ_MAX_MARKS", nullable =  false)
	private String maxMarks;

	@Column(name="QUIZ_NUMBER_OF_QUESTION", nullable =  false)
	private String numberOfQuestion;
	
	@Column(name="IS_ACTIVE", nullable =  false)
	private Integer isActive = 0;
	
	@Column(name="CREATED_AT", nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", nullable =  false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COURSE_ID")
	private Course course;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(String numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Quiz() {
		super();
	}
	
	

}
