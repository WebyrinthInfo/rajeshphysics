package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Dtos.SubjectDto;
import com.rajeshphysics.Models.Subject;
import com.rajeshphysics.Payloads.PageableDataResponse;

public interface SubjectService {
	public SubjectDto addSubject(SubjectDto subjectDto, Long batchId);
	public PageableDataResponse<List<Subject>> getAllSubjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);
}