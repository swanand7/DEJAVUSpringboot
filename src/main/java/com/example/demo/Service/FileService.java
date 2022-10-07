package com.example.demo.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.response.LibraryResponse;

@Service
public interface FileService {
	public String createFingerprint(MultipartFile file) throws IOException, Exception;
	
	public void verifyFingerprint(MultipartFile file);

	public List<LibraryResponse> getAllRecording();
}
