package com.everis.mobile.retrofitsample.retrofit.entities;

import java.io.Serializable;
import java.util.List;

public class CharacterDataContainer implements Serializable {
	private List<Character> results;

	public CharacterDataContainer() {
	}

	public List<Character> getResults() {
		return results;
	}

	public void setResults(List<Character> results) {
		this.results = results;
	}
}
