package com.udacity.gradle.builditbigger;

import android.app.Activity;
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

    private static final int JOKE_ACTIVITY_REQ_CODE = 0;
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
                new FetchJokeAsyncTask(new FetchJokeAsyncTask.TastListener(){

                    @Override
                    public void onTaskFinished(String string) {
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(JokeActivity.EXTRA_JOKE, string);
                        intent.putExtra(JokeActivity.JOKE_KEY, JokeActivity.KEY_PAID);
                        startActivityForResult(intent, JOKE_ACTIVITY_REQ_CODE);
                    }
                }, mProgressBar, mJokeView).execute();
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //When returning from jokeActivity, show the joke layout and hide the progress bar
        if(resultCode == Activity.RESULT_CANCELED){
            mJokeView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}