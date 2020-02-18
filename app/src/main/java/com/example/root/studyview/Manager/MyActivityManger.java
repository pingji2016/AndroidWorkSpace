package com.example.root.studyview.Manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ProjectName:   活动管理类
  * @Package:        com.example.root.studyview.Manager
  * @ClassName:      ActivityManger
  * @Description:     java类作用描述
  * @Author:         ZFM
  * @CreateDate:     2020/2/19 0:38
 */

public class MyActivityManger {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity (Activity activity){
        activities.add(activity);
    }

    public static void removeActivity (Activity activity){
        activities.remove(activity);
    }

    public static void  finishAll (){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
