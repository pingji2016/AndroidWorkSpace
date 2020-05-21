package com.example.root.studyview.MaterialDesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.root.studyview.R;

public class MyFloatingButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floatingview);

        //悬浮按钮点击事件
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.tip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyFloatingButtonActivity.this, "欢迎来到喵星人世界O(∩_∩)O~", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
