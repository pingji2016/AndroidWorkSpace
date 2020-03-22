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
import com.example.root.studyview.Services.BtnChangeTextActivity;
import com.example.root.studyview.Web.JsonViewActivity;
import com.example.root.studyview.Web.WebViewAcitvity;

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

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

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

        btn1.setOnClickListener(new onClickListenerClass());
        btn2.setOnClickListener(new onClickListenerClass());
        btn3.setOnClickListener(new onClickListenerClass());
        btn4.setOnClickListener(new onClickListenerClass());
        btn5.setOnClickListener(new onClickListenerClass());
        btn6.setOnClickListener(new onClickListenerClass());
        btn7.setOnClickListener(new onClickListenerClass());
        btn8.setOnClickListener(new onClickListenerClass());
        btn9.setOnClickListener(new onClickListenerClass());
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
            }
        }
    }
}
