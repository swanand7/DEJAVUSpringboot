package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.FileService;

import java.io.IOException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class AudioController {
	

	@Autowired
	FileService fileService;
		
	@GetMapping("/saveSound")
	public void getAllLocks() throws IOException, InterruptedException{
		try {
            Process proc = Runtime.getRuntime().exec("/home/ubuntu/fingerprint.sh /"); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
             
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
               
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	@PostMapping("/upload")
	  public void uploadFile(@RequestParam("file") MultipartFile file) {
	      fileService.save(file);

	      String message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      System.out.println(message);
	      try {
	            Process proc = Runtime.getRuntime().exec("/home/ubuntu/fingerprint.sh /"); //Whatever you want to execute
	            BufferedReader read = new BufferedReader(new InputStreamReader(
	                    proc.getInputStream()));
	            try {
	                proc.waitFor();
	             
	            } catch (InterruptedException e) {
	                System.out.println(e.getMessage());
	            }
	            while (read.ready()) {
	                System.out.println(read.readLine());
	               
	            }
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	  }
}
