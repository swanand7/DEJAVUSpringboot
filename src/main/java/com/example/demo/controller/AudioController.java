package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.FileService;
import com.example.demo.response.LibraryResponse;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public final class AudioController {

	@Autowired
	FileService fileService;

	@GetMapping("/")
	public String baseApi() {
		return "Application working";

	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		return new ResponseEntity<String>(fileService.createFingerprint(file), HttpStatus.OK);
	}

	@PostMapping("/verify")
	public void varifyFile(@RequestParam("file") MultipartFile file) {
		fileService.verifyFingerprint(file);

	}

	@GetMapping("/library")
	public ResponseEntity<List<LibraryResponse>> getAllAudio() {
		return new ResponseEntity<List<LibraryResponse>>(fileService.getAllRecording(), HttpStatus.OK);
	}
	
	@GetMapping("/library/{id}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer id) throws IOException {
		byte []audioData=fileService.getAudio(id);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(audioData);

	}
}
