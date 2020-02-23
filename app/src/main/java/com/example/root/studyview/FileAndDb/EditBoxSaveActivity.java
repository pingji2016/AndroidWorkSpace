package com.example.root.studyview.FileAndDb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.root.studyview.BookPackage.BuyBookActivity;
import com.example.root.studyview.BroadcastTest.StartLoginActivity;
import com.example.root.studyview.MainActivity;
import com.example.root.studyview.Manager.FileManager;
import com.example.root.studyview.Manager.MyActivityManger;
import com.example.root.studyview.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.System.out;

public class EditBoxSaveActivity extends AppCompatActivity {
    private static final String TAG = "EditBoxSaveActivity";
    private EditText editText;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editbox_save);
        editText = (EditText) findViewById(R.id.editsave);
        Log.d(TAG, "onCreate: 23232");
        String inputStr = load();
        Log.i(TAG, "onCreate: "+ inputStr);
        if (!TextUtils.isEmpty(inputStr)){
            editText.setText(inputStr);
            editText.setSelection(inputStr.length());
            Toast.makeText(this, "XXX", Toast.LENGTH_SHORT).show();
        }


        initData();

        Button sharePerferences = (Button) findViewById(R.id.shanePreferencesBtn);
        sharePerferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                count = count + 1;
                Toast.makeText(EditBoxSaveActivity.this, "count add +1", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name", "Tom@#@#");
                editor.putInt("age", 28);
                editor.putInt("count", count);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button  getSharedBtn = (Button) findViewById(R.id.getSharedBtn);
        getSharedBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences per = getSharedPreferences("data",MODE_PRIVATE);
                String name = per.getString("name", "");
                int age = per.getInt("age", 0);
                count = per.getInt("count", 0);
                boolean married = per.getBoolean("married", false);

                Toast.makeText(EditBoxSaveActivity.this, name + count, Toast.LENGTH_SHORT).show();


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(name);
                builder.setMessage("count ="+ count + "\n age = " + age + "\n married = " + married);
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditBoxSaveActivity.this, "電話", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        Button litePanlBtn = (Button) findViewById(R.id.litepalBtn);
        litePanlBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBoxSaveActivity.this, BuyBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }


    private void initData(){
        SharedPreferences per = getSharedPreferences("data",MODE_PRIVATE);
        count = per.getInt("count", 0);
    }

    private void save(String inputString) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            Log.i(TAG, "save: @#@#" + inputString);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputString);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return  content.toString();
    }
}
