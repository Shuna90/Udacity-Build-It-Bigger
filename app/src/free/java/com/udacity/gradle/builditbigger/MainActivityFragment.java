package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import app.com.example.android.jokeandroidlib.JokeActivity;

/**
 * Created by shuna on 12/30/16.
 */
public class MainActivityFragment extends Fragment {

    private static final int JOKE_ACTIVITY_REQ_CODE = 0;
    private Button mButton;
    private ProgressBar mProgressBar;
    private View mJokeView;
    private AdView mAdView;
    InterstitialAd mInterstitialAd;

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

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                new FetchJokeAsyncTask(new FetchJokeAsyncTask.TastListener(){

                    @Override
                    public void onTaskFinished(String string) {
                        startJokeActivity(string);
                    }
                }, mProgressBar, mJokeView).execute();
            }
        });

        requestNewInterstitial();

        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new FetchJokeAsyncTask(new FetchJokeAsyncTask.TastListener(){

                    @Override
                    public void onTaskFinished(String string) {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            startJokeActivity(string);
                        }
                    }
                }, mProgressBar, mJokeView).execute();

            }
        });

        /*
        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        */
        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void startJokeActivity(String joke){
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        intent.putExtra(JokeActivity.JOKE_KEY, JokeActivity.KEY_FREE);
        startActivityForResult(intent, JOKE_ACTIVITY_REQ_CODE);
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
