package com.community.dto;

public class GithubUser {
	private String name;
	private String bio;
	private Long id;
	public String getName() {
		return name;
	}
	public String getBio() {
		return bio;
	}
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
