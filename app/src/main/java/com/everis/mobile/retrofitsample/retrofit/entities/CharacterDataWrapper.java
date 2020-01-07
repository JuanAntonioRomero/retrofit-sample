package com.everis.mobile.retrofitsample.retrofit.entities;

import java.io.Serializable;

public class CharacterDataWrapper implements Serializable {
	private CharacterDataContainer data;

	public CharacterDataWrapper() {
	}

	public CharacterDataContainer getData() {
		return data;
	}

	public void setData(CharacterDataContainer data) {
		this.data = data;
	}
}
