package com.example.demo.controller;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import com.example.demo.entity.FingerprintEntity;
import com.example.demo.response.AudioResponse;
import com.example.demo.response.LibraryResponse;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public final class AudioController {

	@Autowired
	FileService fileService;
	private final String FOLDER_PATH = "/home/ubuntu/mp3/";

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

//	@GetMapping("/library/{id}")
//	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer id) throws IOException {
//		byte []audioData=fileService.getAudio(id);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.APPLICATION_OCTET_STREAM)
//				.body(audioData);
//
//	}
	@GetMapping("/library1/{id}")
	public ResponseEntity<Resource> downloadImageFromFileSystem1(@PathVariable Integer id) throws IOException {
		FingerprintEntity fingerprintEntity=fileService.getAudio(id);
		String filePath=FOLDER_PATH+fingerprintEntity.getFileName();
		byte []audioData= Files.readAllBytes(new File(filePath).toPath());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(fingerprintEntity.getFileType()))
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "fingerprintEntity; filename=\""+fingerprintEntity.getFileName()+"\"")
				.body(new ByteArrayResource(audioData));

	}
	@GetMapping("/library/{id}")
	public ResponseEntity<AudioResponse> downloadImageFromFileSystem(@PathVariable Integer id) throws IOException {
		FingerprintEntity fingerprintEntity=fileService.getAudio(id);
		String filePath=FOLDER_PATH+fingerprintEntity.getFileName();
		byte []audioData= Files.readAllBytes(new File(filePath).toPath());
		AudioResponse response=new AudioResponse();
		response.setFile(audioData);
		return new ResponseEntity<AudioResponse>(response, HttpStatus.OK);

	}

}
