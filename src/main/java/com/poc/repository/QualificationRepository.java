package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.models.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}