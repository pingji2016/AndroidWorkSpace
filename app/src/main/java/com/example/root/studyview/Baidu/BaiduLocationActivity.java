package com.example.root.studyview.Baidu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.example.root.studyview.Notification.MyNotificationActivity;
import com.example.root.studyview.R;

import java.util.ArrayList;
import java.util.List;

public class BaiduLocationActivity extends AppCompatActivity {
    public LocationClient mLoccationClient;

    private static final String TAG = "BaiduLocationActivity";

    public TextView positionText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baidulayout);
        positionText = (TextView) findViewById(R.id.textLocation);
        mLoccationClient = new LocationClient(getApplicationContext());
        mLoccationClient.registerLocationListener(new MyLocationLister());

        List<String> permissionList = checkpermission();

        if (!permissionList.isEmpty()){
            requsetPermission(permissionList);
        }else {
            requesetLocation();
        }
    }

    public List<String> checkpermission(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            Log.i(TAG, "checkpermission: @1");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
            Log.i(TAG, "checkpermission: @2");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            Log.i(TAG, "checkpermission: @3");
        }
        return permissionList;
    }

    public void requsetPermission(List<String> permissionList){
        String [] permissons = permissionList.toArray(new String[permissionList.size()]);
        ActivityCompat.requestPermissions(this, permissons, 1);
    }

    private void requesetLocation(){
        initLocation();
        mLoccationClient.start();
    }

    public void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLoccationClient.setLocOption(option);
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
                requesetLocation();
            }else {
                Toast.makeText(this, "你死不承认", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoccationClient.stop();
    }

    public class MyLocationLister extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度:").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("纬度:").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);
        }
    }
}
