package com.lixin.hardwarecode;

import android.telephony.TelephonyManager;

import com.lixin.hardwarecode.Hook.Cpuinfo;
import com.lixin.hardwarecode.Hook.GPShook;
import com.lixin.hardwarecode.Hook.Hook;
import com.lixin.hardwarecode.Hook.OpenGL;
import com.lixin.hardwarecode.Hook.Phone;
import com.lixin.hardwarecode.Hook.Resolution;
import com.lixin.hardwarecode.Hook.XBuild;
import com.lixin.hardwarecode.Tools.RandomDevice;
import com.lixin.hardwarecode.Utis.RootCloak;
import com.lixin.hardwarecode.Utis.SharedPref;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Mainhook implements IXposedHookLoadPackage {
//    @Override
//    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
//    //     XposedBridge.log("HOOK  作用于全局" );
//
//            new Hook().HookTest(loadPackageParam); // 动态生效 不用重启
//            new RootCloak().handleLoadPackage(loadPackageParam);
//            new XBuild(loadPackageParam);  //build
//            new Phone(loadPackageParam);  // TelephonyManager
//            new Resolution().Display(loadPackageParam);  // 屏幕
//            new OpenGL().OpenGLTest(loadPackageParam);   // 显卡
//            new Cpuinfo(loadPackageParam);         // CPU*/
//
//
//
//
//        /*
//            GPS位置 只对百度高德生效 有需要的朋友可以添加
//            要更改位置应用的包名  不要作用于全局  某些机型可能不太好使 请自行适配 不难的
//         */
//        if (loadPackageParam.packageName.equals("com.baidu.BaiduMap")||
//                loadPackageParam.packageName.equals("com.autonavi.minimap")) {
//
//            GPShook.HookAndChange(loadPackageParam.classLoader,
//                    SharedPref.getfloatXValue("lat"),SharedPref.getfloatXValue("log"));
//
//
//
//        }
//    }


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("修改IMEI handleLoadPackage执行了");
        //获得Sharedpreference保存的数据
        final XSharedPreferences sPre = new XSharedPreferences(this.getClass().getPackage().getName(), "sPref"); //this.getClass().getPackage().getName()获取当前包名

        XposedHelpers.findAndHookMethod(TelephonyManager.class.getName(), loadPackageParam.classLoader, "getDeviceId", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //具体的hook操作就这一行，设置getDeviceId（）的返回值
                String imei = RandomDevice.makeRandomIMEI();
                param.setResult(imei);
                //打印log
                XposedBridge.log("hook ---getDeviceId***after " + param.getResult());
            }
        });
    }
}
