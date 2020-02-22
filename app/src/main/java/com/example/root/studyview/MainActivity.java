package com.example.root.studyview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.studyview.BroadcastTest.StartLoginActivity;
import com.example.root.studyview.FileAndDb.EditBoxSaveActivity;
import com.example.root.studyview.NewsPackage.NewsContentActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(new onClickListenerClass());
        btn2.setOnClickListener(new onClickListenerClass());
        btn3.setOnClickListener(new onClickListenerClass());
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
            }else {// 进入广播
                Intent intent = new Intent(MainActivity.this, StartLoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
