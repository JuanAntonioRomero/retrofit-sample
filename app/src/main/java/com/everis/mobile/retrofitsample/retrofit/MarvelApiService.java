package com.everis.mobile.retrofitsample.retrofit;

import com.everis.mobile.retrofitsample.retrofit.entities.CharacterDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiService {
	@GET("characters?limit=100&apikey=3b286e26ac6f7907b051d44e86af4fb6&ts=1&hash=e3c884ef86d9e64e2043be7b9faae261")
	Call<CharacterDataWrapper> getCharacterList(@Query("nameStartsWith") String startsWith);
}
