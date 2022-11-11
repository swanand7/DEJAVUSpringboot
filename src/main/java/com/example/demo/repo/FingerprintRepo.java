package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FingerprintEntity;

public interface FingerprintRepo extends JpaRepository<FingerprintEntity, Integer>{

	@Transactional
	FingerprintEntity findByFileName(String originalFilename);
	
	@Modifying
	@Transactional
	@Query("delete from FingerprintEntity f where f.fileName in(:audios)")
	void deleteByIdIn(List<String> audios);

}
