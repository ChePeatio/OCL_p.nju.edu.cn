package util;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Kedar on 2015/9/23.
 *
 * TODO Activity收集以及释放
 */
public class ActivityManagerUtils {

    private ArrayList<Activity> activityList = new ArrayList<>();

    private static ActivityManagerUtils activityManagerUtils;

    private ActivityManagerUtils(){

    }

    public static ActivityManagerUtils getInstance(){
        if(null == activityManagerUtils){
            activityManagerUtils = new ActivityManagerUtils();
        }
        return activityManagerUtils;
    }

    public Activity getTopActivity(){
        return activityList.get(activityList.size()-1);
    }

    public void addActivity(Activity ac){
        activityList.add(ac);
    }

    public void removeAllActivity(){
        for(Activity ac:activityList){
            if(ac != null){
                if(!ac.isFinishing()){
                    ac.finish();
                }
                //ac = null;
            }
        }
        activityList.clear();
    }
}
