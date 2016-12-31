package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import app.com.example.android.jokeandroidlib.JokeActivity;

/**
 * Created by shuna on 12/30/16.
 */
public class MainActivityFragment extends Fragment {

    private Button mButton;
    private ProgressBar mProgressBar;
    private View mJokeView;
    private AdView mAdView;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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


        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }
}
