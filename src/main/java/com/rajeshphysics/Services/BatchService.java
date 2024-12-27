package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Dtos.BatchDto;
import com.rajeshphysics.Models.Batch;
import com.rajeshphysics.Payloads.PageableDataResponse;


public interface BatchService {
	 public BatchDto addBatch(BatchDto batchDto, Long courseId);
	 public PageableDataResponse<List<Batch>> getAllBatch(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);

}
