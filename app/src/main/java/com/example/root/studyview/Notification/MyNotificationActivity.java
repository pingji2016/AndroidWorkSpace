package com.example.root.studyview.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.studyview.FileAndDb.EditBoxSaveActivity;
import com.example.root.studyview.R;

import java.io.File;

import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;
import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

public class MyNotificationActivity extends AppCompatActivity {
    private static final String TAG = "MyNotificationActivity";

    private ImageView picture;

    private Uri imageUri;

    private static final int TAKE_PHONE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_new_notify);
//        Button notify_btn = (Button)findViewById(R.id.notify_btn);
//        notify_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EditBoxSaveActivity.class);
//                PendingIntent pi = PendingIntent.getActivity(view.getContext(), 0, intent, 0);
//                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                Notification notification = new NotificationCompat.Builder(view.getContext())
//                        .setContentTitle("This is Content title")
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("[02:21:17:59:28:824] [I][Util]\tgetJlichtVersion jlVersion__=\t5090900\n" +
//                                "[02:21:17:59:28:825] [I][RNN_JJLiveInterface]\texitJJLive into\n" +
//                                "[02:21:17:59:28:826] [I][RNN_JJLiveInterface]\tcallJJLiveMethod IN , type : \t13\t, dataValue : "))
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                        .setContentIntent(pi)
//                        .setSound(Uri.fromFile(new File("/system/media/audio/notifications/Argon.ogg")))
//                        .setVibrate(new long[]{0, 1000, 1000, 1000})
//                        .setPriority(PRIORITY_MAX)
//                        .setAutoCancel(true)
//                        .build();
//
//                manager.notify(1, notification);
//            }
//        });

        picture = (ImageView) findViewById(R.id.picture);

        // take photo
        Button photo_btn = (Button)findViewById(R.id.photo_btn);
        photo_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24){
                    imageUri = FileProvider.getUriForFile(view.getContext(), "com.example.root.studyview.fileprovider", outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Log.i(TAG, "onClick: "+ imageUri.toString()+"@@@"+ imageUri.getAuthority() +"ZZ");
                // camera start
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
//        Toast.makeText(this, requestCode, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onActivityResult: @@" + requestCode);

        switch (requestCode){
            case TAKE_PHONE:
                if (requestCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
                default:
                    break;
        }
    }
}
