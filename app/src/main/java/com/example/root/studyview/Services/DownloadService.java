package com.example.root.studyview.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.root.studyview.MainActivity;
import com.example.root.studyview.R;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadBinder mBinder = new DownloadBinder();

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManger().notify(1, getNotification("下载中。。。、", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManger().notify(1, getNotification("下载成功！！", -1 ));
            Toast.makeText(DownloadService.this, "下载成功!!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManger().notify(1, getNotification("下载失败？？", -1 ));
            Toast.makeText(DownloadService.this, "下下载失败？？", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "下载停止", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "下载取消", Toast.LENGTH_SHORT).show();
        }
    };

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if (downloadTask == null){
                downloadTask = new DownloadTask(listener);
                downloadUrl = url;
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("开始下载。。", 0));
                Toast.makeText(DownloadService.this, "开始下载", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if (downloadTask != null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if (downloadTask != null){
                downloadTask.cancelDownload();
            }else {
                if (downloadUrl != null){
                    String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(dir + filename);
                    Toast.makeText(DownloadService.this, dir + filename, Toast.LENGTH_SHORT).show();
                    if (file.exists()){
                        file.delete();
                    }
                    getNotificationManger().cancel(1);
                    stopForeground(true);
                }
            }
        }
    }

    private NotificationManager getNotificationManger(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0){
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);

        }
        return builder.build();

    }
}
