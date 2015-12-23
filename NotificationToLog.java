package net.migol.xposed.gmailprivacyfix;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.ArrayList;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;

public class NotificationToLog implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.google.android.gm"))
            return;

        findAndHookConstructor("android.support.v4.app.bz", loadPackageParam.classLoader, Context.class, Notification.class, CharSequence.class, CharSequence.class, CharSequence.class, RemoteViews.class, int.class, PendingIntent.class, PendingIntent.class, Bitmap.class, int.class, int.class, boolean.class, boolean.class, boolean.class, int.class, CharSequence.class, boolean.class, String.class, new ArrayList<String>().getClass(), Bundle.class, int.class, int.class, Notification.class, String.class, boolean.class, String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("Before hook bound ok");
            }
        });
    }
}
