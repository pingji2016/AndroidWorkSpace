package com.example.root.studyview.Services;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_btnchange_txet);
        Button btn = (Button) findViewById(R.id.btn_change_text);
        text  = (TextView) findViewById(R.id.text_change);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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

            default:
                break;
        }
    }
}
