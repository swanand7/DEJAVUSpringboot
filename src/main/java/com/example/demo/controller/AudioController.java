package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class AudioController {
	
	@GetMapping("/saveSound")
	public Process getAllLocks() throws IOException, InterruptedException{
		
		String command = "cd /home/ubuntu/mp3 panako query 2.mp3";

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =  
              new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor(); 
        return proc;
	}
}
