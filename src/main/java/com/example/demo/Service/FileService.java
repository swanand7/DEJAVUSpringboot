package com.example.demo.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	public String createFingerprint(MultipartFile file) throws IOException, Exception;
	
	public void verifyFingerprint(MultipartFile file);
}
