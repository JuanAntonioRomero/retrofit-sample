package com.everis.mobile.retrofitsample.retrofit.entities;

import java.io.Serializable;

public class Character implements Serializable {
	private String name;
	private String description;
	private Image thumbnail;

	public Character() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
}
