package com.example.root.studyview.BroadcastTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.studyview.R;

public class StartLoginActivity extends BroadBaseActivity {
    private EditText nameEdit;
    private EditText pwdEdit;
    private Button loginBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_login);
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        pwdEdit = (EditText) findViewById(R.id.pwdEdit);
        loginBtn = (Button) findViewById(R.id.signInBtn);
        nameEdit.setText("zhaofm");
        pwdEdit.setText("123456");
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account = nameEdit.getText().toString();
                String password = pwdEdit.getText().toString();
                if (account.equals("zhaofm") && password.equals("123456")){
                    Intent intent = new Intent(StartLoginActivity.this, MainContentActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(StartLoginActivity.this, "Pass is Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


     /**
      * @点击事件类
      * @description 按钮点击事件
      * @date: 2020/2/18
      * @param  * @param null
      * @return
      */
    public class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String account = nameEdit.getText().toString();
            String password = pwdEdit.getText().toString();
            if (account.equals("zhaofm") && password.equals("123456")){
                Intent intent = new Intent(StartLoginActivity.this, MainContentActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(StartLoginActivity.this, "Pass is Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
