package com.rajeshphysics.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {

	@Query(value = "SELECT * FROM rajeshphysics.contactus WHERE id LIKE CONCAT('%', :search, '%') OR name LIKE CONCAT('%', :search, '%') OR mobile LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<ContactUs> findByKeyword(@Param("search") String search, PageRequest page);

}
