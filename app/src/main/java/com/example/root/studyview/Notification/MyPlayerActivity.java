package com.example.root.studyview.Notification;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.studyview.R;

import java.io.File;

public class MyPlayerActivity extends AppCompatActivity implements View.OnClickListener{
    private Button stop_btn;

    private Button play_btn;

    private Button pause_btn;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palyer);

        stop_btn = (Button) findViewById(R.id.stop_btn);
        play_btn = (Button) findViewById(R.id.play_btn);
        pause_btn = (Button) findViewById(R.id.pause_btn);

        stop_btn.setOnClickListener(this);
        play_btn.setOnClickListener(this);
        pause_btn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(MyPlayerActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MyPlayerActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer(){
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Heartbeats.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(this, "你死不承认", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play_btn:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.pause_btn:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop_btn:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
