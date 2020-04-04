package com.example.root.studyview.Services;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
* 在默认情况下HttpURLConnection 使用 gzip方式获取，文件 getContentLength() 这个方法，每次read完成后可以获得，当前已经传送了多少数据，而不能用这个方法获取需要传送多少字节的内容，当read() 返回 -1时，读取完成。

    因此要取得正确的文件长度，要求http请求不要gzip压缩。
* */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    private static final String TAG = "DownloadTask";

    private  DownloadListener listener;

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private boolean isCanceled = false;

    private boolean isPaused = false;
    private int lastProgress = 0;

    public DownloadTask(DownloadListener downloadLister){
        this.listener = downloadLister;
    }

    @Override
    protected Integer doInBackground(String... strings) {

        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try{
            long downloadLength = 0;
            String downloadUrl = strings[0];
            String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(dir + filename);
            if (file.exists()){
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            Log.i(TAG, "doInBackground: "+ contentLength);

            if (0 == contentLength){
                return TYPE_FAILED;
            }else if (contentLength >= downloadLength){
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes = " + downloadLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len ;

                while ((len = is.read(b))!= -1){
                    if (isCanceled){
                        return TYPE_CANCELED;
                    }else {
                        total += len;
                        savedFile.write(b, 0 ,len);
                        int progerss = (int)((total + downloadLength)*100 / contentLength);
                        publishProgress(progerss);
                    }
                }
                response.close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (savedFile != null){
                    savedFile.close();
                }
                if (isCanceled&&file!=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
        int progress = values[0];
        if (progress > lastProgress){
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
//        super.onPostExecute(integer);
        if (integer == null) return;
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
                default:
                    break;
        }
    }


    public void cancelDownload(){
        isCanceled = true;
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null&&response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
