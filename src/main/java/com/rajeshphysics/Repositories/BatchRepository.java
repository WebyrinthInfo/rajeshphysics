package com.rajeshphysics.Repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
	
	@Query(value = "SELECT * FROM batches WHERE batch_code = :batchCode", nativeQuery = true)
	Optional<Batch> findByBatchCode(@Param("batchCode") String batchCode);

	
	@Query(value = "SELECT * FROM rajeshphysics.batches WHERE id LIKE CONCAT('%', :search, '%') OR batch_code LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<Batch> findByKeyword(@Param("search") String search, PageRequest page);

}
