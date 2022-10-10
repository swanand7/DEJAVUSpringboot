package com.example.demo.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LibraryResponse {
	private int id;
	private String fileName;
	private String fileType;
	private long fileSize;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime Date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public LocalDateTime getDate() {
		return Date;
	}
	public void setDate(LocalDateTime date) {
		Date = date;
	}
	
	

}
