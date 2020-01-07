package com.everis.mobile.retrofitsample.characterlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.everis.mobile.retrofitsample.R;
import com.everis.mobile.retrofitsample.characterdetail.CharacterDetailActivity;
import com.everis.mobile.retrofitsample.retrofit.MarvelApi;
import com.everis.mobile.retrofitsample.retrofit.entities.Character;
import com.everis.mobile.retrofitsample.retrofit.entities.CharacterDataWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterListFragment extends ListFragment {

    public static final String ARGS_LETTER = "CharacterListFragment_ARGS_LETTER";

    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/characters";
    private static final String URL_ARG_LIMIT = "limit=100"; // Asegurar que obtenemos todos los resultados de una letra
    private static final String URL_ARG_NAME_STARTS_WITH = "nameStartsWith=%s"; // Filtrado por letra
    private static final String URL_ARG_APIKEY = "apikey=3b286e26ac6f7907b051d44e86af4fb6"; // Clave pública del usuario
    private static final String URL_ARG_TIMESTAMP = "ts=1"; // Timestamp fijo para pruebas
    private static final String URL_ARG_HASH = "hash=e3c884ef86d9e64e2043be7b9faae261"; // MD5(timestamp + clave_privada + clave_pública)
    private static final String URL = BASE_URL + "?" + URL_ARG_LIMIT + "&" + URL_ARG_NAME_STARTS_WITH + "&" + URL_ARG_APIKEY + "&" + URL_ARG_TIMESTAMP + "&" + URL_ARG_HASH;

    private List<Character> mCharacterList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        String url = String.format(URL, getArguments().getString(ARGS_LETTER));
//
//        new Api.ApiCallGet(new Api.ApiCallListener() {
//            @Override
//            public void success(String response) {
//                try {
//                    mCharacterList = new ArrayList<>();
//
//                    JSONObject json = new JSONObject(response);
//                    JSONArray characterListJson = json.getJSONObject("data").getJSONArray("results");
//                    int characterCount = characterListJson.length();
//                    for (int i = 0; i < characterCount; i++) {
//                        JSONObject characterJson = characterListJson.getJSONObject(i);
//                        MarvelCharacter character = new MarvelCharacter();
//                        character.setName(characterJson.getString("name"));
//                        character.setDescription(characterJson.getString("description"));
//                        JSONObject thumbnailJson = characterJson.getJSONObject("thumbnail");
//                        character.setThumbnailUrl(thumbnailJson.getString("path"));
//                        character.setThumbnailExtension(thumbnailJson.getString("extension"));
//                        mCharacterList.add(character);
//                    }
//
//                    setListAdapter(new CharacterAdapter(mCharacterList));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).execute(url);

        getCharacterList(getArguments().getString(ARGS_LETTER));

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CharacterDetailActivity.class);
                intent.putExtra(CharacterDetailActivity.ARGS_CHARACTER, mCharacterList.get(position));
                startActivity(intent);
            }
        });
    }

    private void getCharacterList(String startingLetter) {
        // Usando solo Retrofit
//        new MarvelApi().getMarvelApiService().getCharacterList(startingLetter).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(getActivity(), "JSON recibido: " + response.body(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error al hacer la llamada", Toast.LENGTH_LONG).show();
//            }
//        });

        // Usando Retrofit con Moshi
        new MarvelApi().getMarvelApiService().getCharacterList(startingLetter).enqueue(new Callback<CharacterDataWrapper>() {
            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                mCharacterList = response.body().getData().getResults();
//                Toast.makeText(getActivity(), "JSON recibido: " + mCharacterList.size() + " items", Toast.LENGTH_SHORT).show();
                setListAdapter(new CharacterAdapter(mCharacterList));
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al hacer la llamada", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class CharacterAdapter extends BaseAdapter {

        private List<Character> mCharacterList;

        public CharacterAdapter(List<Character> characterList) {
            mCharacterList = characterList;
        }

        @Override
        public int getCount() {
            return mCharacterList.size();
        }

        @Override
        public Character getItem(int position) {
            return mCharacterList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }
            ((TextView) convertView).setText(getItem(position).getName());
            return convertView;
        }
    }
}
