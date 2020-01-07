package com.everis.mobile.retrofitsample.retrofit;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MarvelApi {

	private static final String BASE_URL = "https://gateway.marvel.com/v1/public/"; // Debe terminar en '/'

	private static Retrofit retrofitService = null;
	private static MarvelApiService marvelApiService = null;

	public MarvelApi() {
		if (retrofitService == null) {
			retrofitService = new Retrofit.Builder()
//					.addConverterFactory(ScalarsConverterFactory.create()) // Conversor estándar
					.addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build())) // Conversor de Moshi
					.baseUrl(BASE_URL)
					.build();
			marvelApiService = retrofitService.create(MarvelApiService.class);
		}
	}

	public MarvelApiService getMarvelApiService() {
		return marvelApiService;
	}
}
