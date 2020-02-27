package com.example.root.studyview.Web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.studyview.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebViewAcitvity extends AppCompatActivity implements View.OnClickListener {
    private TextView responseText;
    private static final String TAG = "WebViewAcitvity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
//        WebView webView = (WebView) findViewById(R.id.web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://www.baidu.com");

        Button web_btn = (Button)findViewById(R.id.web_btn);
        Button xml_btn = (Button)findViewById(R.id.xml_btn);
        responseText = (TextView)findViewById(R.id.response_text);
        web_btn.setOnClickListener(this);
        xml_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.web_btn) {
            sendRequestOkHttp();
        }else if (view.getId() == R.id.xml_btn) {
            sendRequestOkHttp2();
        }
    }

    public void sendRequestOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();

                    Response response = client.newCall(request).execute();
                    String responeData = response.body().string();
                    showResopnse(responeData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendRequestOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.199.228:81/app.xml")
                            .build();

                    Response response = client.newCall(request).execute();
                    String responeData = response.body().string();
                    showXMLResopnse(responeData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showResopnse(final String responses) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(responses);
            }
        });
    }

    public void showXMLResopnse(final String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        switch (nodeName){
                            case "id":
                                id = xmlPullParser.nextText();
                                break;
                            case "name":
                                name = xmlPullParser.nextText();
                                break;
                            case "version": {
                                version = xmlPullParser.nextText();
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodeName)){
                            Log.i(TAG, "showXMLResopnse: id = " + id);
                            Log.i(TAG, "showXMLResopnse: name = " + name);
                            Log.i(TAG, "showXMLResopnse: version = " + version);
                        }
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
