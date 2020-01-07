package com.everis.mobile.retrofitsample.characterdetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.everis.mobile.retrofitsample.R;
import com.everis.mobile.retrofitsample.model.MarvelCharacter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class CharacterDetailFragment extends Fragment {

    public static final String ARGS_CHARACTER = "CharacterDetailFragment_ARGS_CHARACTER";

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        mNameTextView = view.findViewById(R.id.text_name);
        mDescriptionTextView = view.findViewById(R.id.text_description);
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MarvelCharacter character = (MarvelCharacter) getArguments().getSerializable(ARGS_CHARACTER);
        mNameTextView.setText(character.getName());
        mDescriptionTextView.setText(character.getDescription());
        mImageView.setImageResource(R.drawable.logo);

        new DownloadImagesTask().execute(new String[]{character.getImageUrl(MarvelCharacter.SIZE_MEDIUM)});
    }

    private class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return download_Image(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bitmap = null;
            BufferedInputStream bis = null;
            try {
                URLConnection conn = new URL(url).openConnection();
                conn.connect();
                bis = new BufferedInputStream(conn.getInputStream());
                bitmap = BitmapFactory.decodeStream(bis);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
            return bitmap;
        }
    }
}
