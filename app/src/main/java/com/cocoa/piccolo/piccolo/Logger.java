package com.cocoa.piccolo.piccolo;

import android.util.Log;
import android.view.View;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.Logger
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/14 14:48
 */
public class Logger {
    
    public static void log(View view){
        Log.e("----",view.toString());
    }

}
