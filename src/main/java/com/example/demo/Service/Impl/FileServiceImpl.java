package com.example.demo.Service.Impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.FileService;

@Service
public class FileServiceImpl implements FileService{
	
	private final Path root = Paths.get("/home/ubuntu/mp3");

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
		      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
	}

}
