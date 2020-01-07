package com.everis.mobile.retrofitsample.characterdetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.everis.mobile.retrofitsample.R;
import com.everis.mobile.retrofitsample.model.MarvelCharacter;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARGS_CHARACTER = "CharacterDetailActivity_ARGS_CHARACTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MarvelCharacter character = (MarvelCharacter) getIntent().getSerializableExtra(ARGS_CHARACTER);

        setContentView(R.layout.activity_character_list);

        Fragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(CharacterDetailFragment.ARGS_CHARACTER, character);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
