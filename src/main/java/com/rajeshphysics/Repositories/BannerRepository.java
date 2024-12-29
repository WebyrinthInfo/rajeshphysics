package com.rajeshphysics.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajeshphysics.Models.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}
