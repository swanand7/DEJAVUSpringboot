package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FingerprintEntity;

public interface FingerprintRepo extends JpaRepository<FingerprintEntity, String>{

	@Transactional
	FingerprintEntity findByFileName(String originalFilename);

}
