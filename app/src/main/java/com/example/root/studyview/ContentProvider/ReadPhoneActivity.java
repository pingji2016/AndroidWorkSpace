package com.example.root.studyview.ContentProvider;
/**
  *
  * @ProjectName:
  * @Package:        com.example.root.studyview.ContentProvider
  * @ClassName:      ReadPhoneActivity
  * @Description:     读取手机通讯录
  * @Author:         ZFM
  * @CreateDate:     2020/2/24 22:04
 */

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.studyview.R;

import java.util.ArrayList;
import java.util.List;

public class ReadPhoneActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> contactsList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_contacts);
        ListView contactsView = (ListView)findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, contactsList);
        contactsView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new  String[]{
                    Manifest.permission.READ_CONTACTS}, 1);
            }else {
            readContacts();
        }
    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null,null);
            if (cursor != null){
                while (cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    Toast.makeText(this, "你死不承认", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }
}
