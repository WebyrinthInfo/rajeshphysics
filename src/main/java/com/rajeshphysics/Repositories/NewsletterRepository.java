package com.rajeshphysics.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Newsletter;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

	@Query(value = "SELECT * FROM rajeshphysics.newsletters WHERE mobile = :mobile", nativeQuery = true)
	Optional<Newsletter> findByMobile(@Param("mobile") String mobile);
	

}
