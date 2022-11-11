package com.example.demo.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FingerprintEntity;
import com.example.demo.model.ResultModel;
import com.example.demo.response.LibraryResponse;

@Service
public interface FileService {
	public String createFingerprint(MultipartFile file, String length) throws IOException, Exception;
	
	public List<ResultModel> verifyFingerprint(MultipartFile file);

	public List<LibraryResponse> getAllRecording();

	public FingerprintEntity getAudio(Integer id) throws IOException;

	public String deleteFromLibrary(List<String> songIds);
}
