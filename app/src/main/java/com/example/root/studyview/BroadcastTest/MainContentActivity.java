package com.example.root.studyview.BroadcastTest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.root.studyview.R;

public class MainContentActivity extends BroadBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        Button forceOffline = (Button) findViewById(R.id.submitBtn);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.root.studyview.BroadcastTest.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
