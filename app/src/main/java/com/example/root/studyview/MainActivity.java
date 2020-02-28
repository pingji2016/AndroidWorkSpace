package com.example.root.studyview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.studyview.BroadcastTest.StartLoginActivity;
import com.example.root.studyview.ContentProvider.ReadPhoneActivity;
import com.example.root.studyview.FileAndDb.EditBoxSaveActivity;
import com.example.root.studyview.NewsPackage.NewsContentActivity;
import com.example.root.studyview.Notification.MyNotificationActivity;
import com.example.root.studyview.Web.JsonViewActivity;
import com.example.root.studyview.Web.WebViewAcitvity;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);

        btn1.setOnClickListener(new onClickListenerClass());
        btn2.setOnClickListener(new onClickListenerClass());
        btn3.setOnClickListener(new onClickListenerClass());
        btn4.setOnClickListener(new onClickListenerClass());
        btn5.setOnClickListener(new onClickListenerClass());
        btn6.setOnClickListener(new onClickListenerClass());
        btn7.setOnClickListener(new onClickListenerClass());
        btn8.setOnClickListener(new onClickListenerClass());
    }

    private class onClickListenerClass implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn1){//  进入新闻
                Intent intent = new Intent(MainActivity.this, NewsContentActivity.class);
                startActivity(intent);
            }else if (view == btn2){ // 进入文件保存
                Intent intent = new Intent(MainActivity.this, EditBoxSaveActivity.class);
                startActivity(intent);
            }else if (view == btn3){// 进入广播
                Intent intent = new Intent(MainActivity.this, StartLoginActivity.class);
                startActivity(intent);
            }else if (view == btn4){// 进入广播
                Intent intent = new Intent(MainActivity.this, ReadPhoneActivity.class);
                startActivity(intent);
            }else if (view == btn5){// 进入通知
                Intent intent = new Intent(MainActivity.this, MyNotificationActivity.class);
                startActivity(intent);
            }else if (view == btn6){// 进入通知
                Intent intent = new Intent(MainActivity.this, WebViewAcitvity.class);
                startActivity(intent);
            }else if (view == btn7){// 进入通知
                Intent intent = new Intent(MainActivity.this, JsonViewActivity.class);
                startActivity(intent);
            }else if (view == btn8){// 进入通知
                Intent intent = new Intent(MainActivity.this, WebViewAcitvity.class);
                startActivity(intent);
            }
        }
    }
}
