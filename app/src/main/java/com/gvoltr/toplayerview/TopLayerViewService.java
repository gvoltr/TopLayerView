package com.gvoltr.toplayerview;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by stanislavgavrosh on 9/15/15.
 */
public class TopLayerViewService extends Service{

    private WindowManager mWindowManager;
    private ImageView mSplashView;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Toast.makeText(TopLayerViewService.this, "Android clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mSplashView = new ImageView(this);
        mSplashView.setImageResource(R.mipmap.ic_launcher);
        mSplashView.setOnTouchListener(mOnTouchListener);
        mSplashView.setClickable(true);
        mSplashView.setBackground(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                //you can block whole UI if show this image as splash
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;

        mWindowManager.addView(mSplashView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSplashView != null) mWindowManager.removeView(mSplashView);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
