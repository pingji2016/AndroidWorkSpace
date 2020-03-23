package com.example.root.studyview.Services;

import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    private  DownloadListener listener;

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    public DownloadTask(DownloadListener downloadLister){
        this.listener = downloadLister;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
