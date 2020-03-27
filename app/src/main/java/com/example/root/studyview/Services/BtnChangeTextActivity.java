package com.example.root.studyview.Services;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.studyview.R;

public class BtnChangeTextActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;

    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    // ui线程
                    text.setText("NIce To meet you");
                    break;
                    default:
                        break;
            }
        }
    };

    private  DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (DownloadService.DownloadBinder)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_btnchange_txet);
        Button btn = (Button) findViewById(R.id.btn_change_text);
        Button btn_pausedown = (Button) findViewById(R.id.btn_pausedown);
        Button btn_startdown = (Button) findViewById(R.id.btn_startdown);
        Button btn_canceldown = (Button) findViewById(R.id.btn_canceldown);
        text  = (TextView) findViewById(R.id.text_change);

        btn.setOnClickListener(this);
        btn_startdown.setOnClickListener(this);
        btn_pausedown.setOnClickListener(this);
        btn_canceldown.setOnClickListener(this);

        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new  String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View view) {
        if (downloadBinder == null){
            return;
        }

        switch (view.getId()){
            case R.id.btn_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;

            case R.id.btn_startdown:
                String url = "https://mirrors.tuna.tsinghua.edu.cn/centos/filelist.gz";
                downloadBinder.startDownload(url);
                break;

            case R.id.btn_pausedown:
                downloadBinder.pauseDownload();
                break;

            case R.id.btn_canceldown:
                downloadBinder.cancelDownload();
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "给我权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
