package com.example.root.studyview.Notification;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
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
import java.io.InputStream;

import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;
import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

public class MyNotificationActivity extends AppCompatActivity {
    private static final String TAG = "MyNotificationActivity";

    private ImageView picture;

    private Uri imageUri;

    private static final int TAKE_PHONE = 1;

    private static final int CHOOSE_PHONE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_new_notify);
        Button notify_btn = (Button)findViewById(R.id.notify_btn);
        notify_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditBoxSaveActivity.class);
                PendingIntent pi = PendingIntent.getActivity(view.getContext(), 0, intent, 0);
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(view.getContext())
                        .setContentTitle("This is Content title")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("[02:21:17:59:28:824] [I][Util]\tgetJlichtVersion jlVersion__=\t5090900\n" +
                                "[02:21:17:59:28:825] [I][RNN_JJLiveInterface]\texitJJLive into\n" +
                                "[02:21:17:59:28:826] [I][RNN_JJLiveInterface]\tcallJJLiveMethod IN , type : \t13\t, dataValue : "))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setSound(Uri.fromFile(new File("/system/media/audio/notifications/Argon.ogg")))
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setPriority(PRIORITY_MAX)
                        .setAutoCancel(true)
                        .build();

                manager.notify(1, notification);
            }
        });

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

        // open album
        Button choose_from_album = (Button)findViewById(R.id.choose_from_album);
        choose_from_album.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MyNotificationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MyNotificationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else {
                    openAlbum();
                }
            }
        });

        // open media
        Button media_btn = (Button)findViewById(R.id.media_btn);
        media_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyNotificationActivity.this, MyPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this, "你死不承认", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
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
                        InputStream xx = getContentResolver().openInputStream(imageUri);
                        byte[] buf = new byte[4096];
                        int len = 0;
                        String sx = "";
                        while((len=xx.read(buf))!=-1){
                            String xxx = new String(buf,0,len);
                            sx = sx + xxx;
                        }
                        Log.i(TAG, "onActivityResult: " + sx);

                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch (Exception e){
                        Log.i(TAG, "onActivityResult: @1 picture noNo");
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHONE:
                if (requestCode == CHOOSE_PHONE){
                    if (Build.VERSION.SDK_INT >= 19){
                        handleImageONKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
                default:
                    break;
        }
    }

    @TargetApi(19)
    private void handleImageONKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "="+ id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if("com.android.providers.download.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        Log.i(TAG, "handleImageONKitKat:" + imagePath);
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        Log.i(TAG, "handleImageONKitKat:" + imagePath);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        Log.i(TAG, "displayImage: "+ imagePath);
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this, "XXXX", Toast.LENGTH_SHORT).show();
        }
    }
}
