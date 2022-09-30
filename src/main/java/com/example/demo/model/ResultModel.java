package com.example.demo.model;

public class ResultModel {
	private String Index;
	private String querySong;
	private String queryStart;
	private String queryStop;
	private String matchSong;
	private String matchId;
	private String matchStart;
	private String matchStop;
	private String matchScore;
	private String timeFactor;
	private String frequencyFactor;
	private String matchPercent;

	public String getIndex() {
		return Index;
	}

	public void setIndex(String index) {
		Index = index;
	}

	public String getQuerySong() {
		return querySong;
	}

	public void setQuerySong(String querySong) {
		this.querySong = querySong;
	}

	public String getQueryStart() {
		return queryStart;
	}

	public void setQueryStart(String queryStart) {
		this.queryStart = queryStart;
	}

	public String getQueryStop() {
		return queryStop;
	}

	public void setQueryStop(String queryStop) {
		this.queryStop = queryStop;
	}

	public String getMatchSong() {
		return matchSong;
	}

	public void setMatchSong(String matchSong) {
		this.matchSong = matchSong;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getMatchStart() {
		return matchStart;
	}

	public void setMatchStart(String matchStart) {
		this.matchStart = matchStart;
	}

	public String getMatchStop() {
		return matchStop;
	}

	public void setMatchStop(String matchStop) {
		this.matchStop = matchStop;
	}

	public String getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(String matchScore) {
		this.matchScore = matchScore;
	}

	public String getTimeFactor() {
		return timeFactor;
	}

	public void setTimeFactor(String timeFactor) {
		this.timeFactor = timeFactor;
	}

	public String getFrequencyFactor() {
		return frequencyFactor;
	}

	public void setFrequencyFactor(String frequencyFactor) {
		this.frequencyFactor = frequencyFactor;
	}

	public String getMatchPercent() {
		return matchPercent;
	}

	public void setMatchPercent(String matchPercent) {
		this.matchPercent = matchPercent;
	}

	@Override
	public String toString() {
		return "Result [Index=" + Index + ", querySong=" + querySong + ", queryStart=" + queryStart + ", queryStop="
				+ queryStop + ", matchSong=" + matchSong + ", matchId=" + matchId + ", matchStart=" + matchStart
				+ ", matchStop=" + matchStop + ", matchScore=" + matchScore + ", timeFactor=" + timeFactor
				+ ", frequencyFactor=" + frequencyFactor + ", matchPercent=" + matchPercent + "]";
	}

}
