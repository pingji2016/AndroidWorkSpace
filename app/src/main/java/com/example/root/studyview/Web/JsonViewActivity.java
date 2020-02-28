package com.example.root.studyview.Web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.root.studyview.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonViewActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "JsonViewActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson_json);

        Button json_btn = (Button)findViewById(R.id.json_btn);
        json_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.json_btn){
            sendRequest();
        }
    }

    public void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.199.228:81/app.json")
                            .build();

                    Response response = client.newCall(request).execute();
                    String responeData = response.body().string();
                    showJsonResopnse(responeData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showJsonResopnse(String jsonData){
        Gson gson = new Gson();
        List<AppBean> appList = gson.fromJson(jsonData, new TypeToken<List<AppBean>>(){}.getType());
        for(AppBean app:appList){
            Log.i(TAG, "showJsonResopnse: id = "+ app.getId());
            Log.i(TAG, "showJsonResopnse: name = "+ app.getName());
            Log.i(TAG, "showJsonResopnse: version = "+ app.getVersion());
        }
    }
}
