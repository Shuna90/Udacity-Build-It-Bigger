package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

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
                new EndpointsAsyncTask().execute(new Pair<Context, String>(getContext(), "Manfred"));
            }
        });

        return root;
    }
}