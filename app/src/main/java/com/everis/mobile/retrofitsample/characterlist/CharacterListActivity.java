package com.everis.mobile.retrofitsample.characterlist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.everis.mobile.retrofitsample.R;

public class CharacterListActivity extends AppCompatActivity {

    public static final String ARGS_LETTER = "CharacterListActivity_ARGS_LETTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String letter = getIntent().getStringExtra(ARGS_LETTER);

        setContentView(R.layout.activity_character_list);

        Fragment fragment = new CharacterListFragment();
        Bundle args = new Bundle();
        args.putString(CharacterListFragment.ARGS_LETTER, letter);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
