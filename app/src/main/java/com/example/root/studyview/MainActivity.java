package com.example.root.studyview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.studyview.Baidu.BaiduLocationActivity;
import com.example.root.studyview.BroadcastTest.StartLoginActivity;
import com.example.root.studyview.ContentProvider.ReadPhoneActivity;
import com.example.root.studyview.FileAndDb.EditBoxSaveActivity;
import com.example.root.studyview.MaterialDesign.MyToolBarActivity;
import com.example.root.studyview.NewsPackage.NewsContentActivity;
import com.example.root.studyview.Notification.MyNotificationActivity;
import com.example.root.studyview.Services.BtnChangeTextActivity;
import com.example.root.studyview.Web.JsonViewActivity;
import com.example.root.studyview.Web.WebViewAcitvity;

import java.util.ArrayList;
import java.util.List;

/***
 *            .,,       .,:;;iiiiiiiii;;:,,.     .,,
 *          rGB##HS,.;iirrrrriiiiiiiiiirrrrri;,s&##MAS,
 *         r5s;:r3AH5iiiii;;;;;;;;;;;;;;;;iiirXHGSsiih1,
 *            .;i;;s91;;;;;;::::::::::::;;;;iS5;;;ii:
 *          :rsriii;;r::::::::::::::::::::::;;,;;iiirsi,
 *       .,iri;;::::;;;;;;::,,,,,,,,,,,,,..,,;;;;;;;;iiri,,.
 *    ,9BM&,            .,:;;:,,,,,,,,,,,hXA8:            ..,,,.
 *   ,;&@@#r:;;;;;::::,,.   ,r,,,,,,,,,,iA@@@s,,:::;;;::,,.   .;.
 *    :ih1iii;;;;;::::;;;;;;;:,,,,,,,,,,;i55r;;;;;;;;;iiirrrr,..
 *   .ir;;iiiiiiiiii;;;;::::::,,,,,,,:::::,,:;;;iiiiiiiiiiiiri
 *   iriiiiiiiiiiiiiiii;;;::::::::::::::::;;;iiiiiiiiiiiiiiiir;
 *  ,riii;;;;;;;;;;;;;:::::::::::::::::::::::;;;;;;;;;;;;;;iiir.
 *  iri;;;::::,,,,,,,,,,:::::::::::::::::::::::::,::,,::::;;iir:
 * .rii;;::::,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,::::;;iri
 * ,rii;;;::,,,,,,,,,,,,,:::::::::::,:::::,,,,,,,,,,,,,:::;;;iir.
 * ,rii;;i::,,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,,::i;;iir.
 * ,rii;;r::,,,,,,,,,,,,,:,:::::,:,:::::::,,,,,,,,,,,,,::;r;;iir.
 * .rii;;rr,:,,,,,,,,,,,,,,:::::::::::::::,,,,,,,,,,,,,:,si;;iri
 *  ;rii;:1i,,,,,,,,,,,,,,,,,,:::::::::,,,,,,,,,,,,,,,:,ss:;iir:
 *  .rii;;;5r,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,sh:;;iri
 *   ;rii;:;51,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.:hh:;;iir,
 *    irii;::hSr,.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.,sSs:;;iir:
 *     irii;;:iSSs:.,,,,,,,,,,,,,,,,,,,,,,,,,,,..:135;:;;iir:
 *      ;rii;;:,r535r:...,,,,,,,,,,,,,,,,,,..,;sS35i,;;iirr:
 *       :rrii;;:,;1S3Shs;:,............,:is533Ss:,;;;iiri,
 *        .;rrii;;;:,;rhS393S55hh11hh5S3393Shr:,:;;;iirr:
 *          .;rriii;;;::,:;is1h555555h1si;:,::;;;iirri:.
 *            .:irrrii;;;;;:::,,,,,,,,:::;;;;iiirrr;,
 *               .:irrrriiiiii;;;;;;;;iiiiiirrrr;,.
 *                  .,:;iirrrrrrrrrrrrrrrrri;:.
 *                        ..,:::;;;;:::,,.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;

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
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);

        btn1.setOnClickListener(new onClickListenerClass());
        btn2.setOnClickListener(new onClickListenerClass());
        btn3.setOnClickListener(new onClickListenerClass());
        btn4.setOnClickListener(new onClickListenerClass());
        btn5.setOnClickListener(new onClickListenerClass());
        btn6.setOnClickListener(new onClickListenerClass());
        btn7.setOnClickListener(new onClickListenerClass());
        btn8.setOnClickListener(new onClickListenerClass());
        btn9.setOnClickListener(new onClickListenerClass());
        btn10.setOnClickListener(new onClickListenerClass());
        btn11.setOnClickListener(new onClickListenerClass());
        btn12.setOnClickListener(new onClickListenerClass());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0) {
                    for (int result :grantResults){
                        if (PackageManager.PERMISSION_GRANTED != result){
                            Toast.makeText(this, "需要全部同意权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }else {
                    Toast.makeText(this, "你死不承认", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
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
            }else if (view == btn9){// 进入通知
                Intent intent = new Intent(MainActivity.this, BtnChangeTextActivity.class);
                startActivity(intent);
            }else if (view == btn10){// 进入通知
                Intent intent = new Intent(MainActivity.this, BaiduLocationActivity.class);
                startActivity(intent);
            }else if (view == btn11){// 进入通知
                Intent intent = new Intent(MainActivity.this, MyToolBarActivity.class);
                startActivity(intent);
            }else if (view == btn12){// 进入通知
                Intent intent = new Intent(MainActivity.this, BaiduLocationActivity.class);
                startActivity(intent);
            }
        }
    }
}
