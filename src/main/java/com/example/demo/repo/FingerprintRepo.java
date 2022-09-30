package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FingerprintEntity;

public interface FingerprintRepo extends JpaRepository<FingerprintEntity, String>{

	FingerprintEntity findByFileName(String originalFilename);

}
