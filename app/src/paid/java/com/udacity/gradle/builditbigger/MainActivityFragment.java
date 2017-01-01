package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import app.com.example.android.jokeandroidlib.JokeActivity;

/**
 * Created by shuna on 12/30/16.
 */
public class MainActivityFragment extends Fragment {

    private Button mButton;
    private ProgressBar mProgressBar;
    private View mJokeView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mJokeView = root.findViewById(R.id.joke_view);
        mButton = (Button)root.findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(new EndpointsAsyncTask.TastListener(){

                    @Override
                    public void onTaskFinished(String string) {
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(JokeActivity.EXTRA_JOKE, string);
                        startActivity(intent);
                    }
                }).execute();
            }
        });

        return root;
    }
}