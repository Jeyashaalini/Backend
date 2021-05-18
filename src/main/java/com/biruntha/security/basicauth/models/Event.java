package com.biruntha.security.basicauth.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class Event {

	@Id
	private String id;
	private String title;
	private String image;
	private String imageUrl;
	private String description;
	private String date;

	public Event(String id, String title, String imageUrl, String description, String image, String date) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.description = description;
		this.image = image;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
