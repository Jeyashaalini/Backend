package com.biruntha.security.basicauth.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Upload {
	@Id
	private String id;
	private String title;
	private String artistName;
	private String medium;
	private String category;
	private String size;
	private String description;
	private long price;
	List<String> image;
	private String phoneNumber;

	public Upload( String title, String artistName, String medium, String category, String size,
			String description, long price, List<String> image, String phoneNumber) {
		super();
		
		this.title = title;
		this.artistName = artistName;
		this.medium = medium;
		this.category = category;
		this.size = size;
		this.description = description;
		this.price = price;
		this.image = image;
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId( String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public List<String> getImage() {
		return image;
	}
	public void setImage(List<String> image) {
		this.image = image;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
