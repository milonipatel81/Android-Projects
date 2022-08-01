package com.example.edithapp.config;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.example.edithapp.R;

public class SplashLoader extends AsyncTask<Void, Void, Void> {
    private ProgressDialog dialog;
    Activity activity;
    Context context;
    MediaPlayer mediaPlayer;

    public SplashLoader(Activity activity,Context context) {
        dialog = new ProgressDialog(activity);
        this.context = context;
        this.activity = activity;
        mediaPlayer = MediaPlayer.create(context, R.raw.startup);
    }

    @Override
    protected void onPreExecute() {
        mediaPlayer.start();
    }
    @Override
    protected Void doInBackground(Void... args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}