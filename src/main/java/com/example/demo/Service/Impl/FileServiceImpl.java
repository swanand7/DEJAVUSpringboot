package com.example.demo.Service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.FileService;
import com.example.demo.constants.ScriptFilesConstant;
import com.example.demo.entity.FingerprintEntity;
import com.example.demo.model.ResultModel;
import com.example.demo.repo.FingerprintRepo;
import com.example.demo.response.LibraryResponse;

@Service
public class FileServiceImpl implements FileService {

	private final Path fingerprintPath = Paths.get("/home/ubuntu/mp3");
	private final Path varifyPath = Paths.get("/home/ubuntu/test");

	@Autowired
	private FingerprintRepo fingerprintRepo;

	@Override
	public String createFingerprint(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		if(fingerprintRepo.findByFileName(file.getOriginalFilename())!=null) {
			throw new Exception("Audio with tag "+file.getOriginalFilename()+" available!!");
		}
		try {
			Files.copy(file.getInputStream(), this.fingerprintPath.resolve(file.getOriginalFilename()));
			String message = "Uploaded the file for fingerprinting successfully: " + file.getOriginalFilename();
			System.out.println(message);
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		String output=runScript(true);
		saveAudio(file);
		return output;

	}

	@Override
	public void verifyFingerprint(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			Files.copy(file.getInputStream(), this.varifyPath.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		processOutput(runScript(false).substring(189));
	}

	private void processOutput(String output) {
		String[] arr = output.split("; ");
		List<ResultModel> list = new ArrayList<ResultModel>();
		int chunk = 12;
		for (int i = 0; i < arr.length; i += chunk) {
			ResultModel r = new ResultModel();
			r.setIndex(arr[i]);
			r.setQuerySong(arr[i + 1]);
			r.setQueryStop(arr[i + 2]);
			r.setQueryStop(arr[i + 3]);
			r.setMatchSong(arr[i + 4]);
			r.setMatchId(arr[i + 5]);
			r.setMatchStart(arr[i + 6]);
			r.setMatchStop(arr[i + 7]);
			r.setMatchScore(arr[i + 8]);
			r.setTimeFactor(arr[i + 9]);
			r.setFrequencyFactor(arr[i + 10]);
			r.setMatchPercent(arr[i + 11]);
			list.add(r);
		}
		for (ResultModel i : list) {
			System.out.println(i);
		}
	}

	private String runScript(Boolean isFingerprint) {
		// TODO Auto-generated method stub
		String output = "";
		try {
			System.out.println("Starting script for fingerprinting");
			Process proc;
			if (isFingerprint) {
				proc = Runtime.getRuntime().exec(ScriptFilesConstant.fingerPrint); // Whatever you want to
			} else {
				proc = Runtime.getRuntime().exec(ScriptFilesConstant.verification);
			}
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			output = read.lines().collect(Collectors.joining());
			try {
				proc.waitFor();

			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				return output;

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return output;
	}

	private void saveAudio(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		FingerprintEntity audio = new FingerprintEntity();
		audio.setFileName(file.getOriginalFilename());
		audio.setFileType(file.getContentType());
		audio.setDate(LocalDateTime.now());
		audio.setData(file.getBytes());
		audio.setFileSize(file.getSize());
		fingerprintRepo.save(audio);

	}

	@Override
	public List<LibraryResponse> getAllRecording() {
		List<FingerprintEntity>fingerprints= fingerprintRepo.findAll();
		List<LibraryResponse> list=new ArrayList<>();
		for(FingerprintEntity i:fingerprints) {
			LibraryResponse response=new LibraryResponse();
			response.setId(i.getId());
			response.setFileName(i.getFileName());
			response.setFileSize(i.getFileSize());
			response.setData(i.getData());
			response.setFileType(i.getFileType());
			response.setDate(i.getDate());
			list.add(response);
		}
		return list;
	}

}
