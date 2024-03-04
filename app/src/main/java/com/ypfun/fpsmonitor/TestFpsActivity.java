/*
 * Tencent is pleased to support the open source community by making wechat-matrix available.
 * Copyright (C) 2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ypfun.fpsmonitor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.ypfun.fpslibrary.FpsMonitor;
import com.ypfun.fpslibrary.FrameDecorator;

import java.util.Random;
import java.util.concurrent.Executor;


/**
 * Created by caichongyang on 2017/11/14.
 */

public class TestFpsActivity extends Activity  {
    private static final String TAG = "Matrix.TestFpsActivity";
    private ListView mListView;
    private FrameDecorator decorator;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private static HandlerThread sHandlerThread = new HandlerThread("test");
    private static final int PERMISSION_REQUEST_CODE = 0x02;

    static {
        sHandlerThread.start();
    }

    private int count;
    private long time = System.currentTimeMillis();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fps_layout);


        time = System.currentTimeMillis();
        mListView = (ListView) findViewById(R.id.list_view);
        String[] data = new String[200];
        for (int i = 0; i < 200; i++) {
            data[i] = "MatrixTrace:" + i;
        }
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SystemClock.sleep(80);
                return false;
            }
        });
        mListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, data) {
            Random random = new Random();

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        int rand = random.nextInt(10);
//                        SystemClock.sleep(rand * 4);
//                    }
//                });
                return super.getView(position, convertView, parent);
            }
        });
        FpsMonitor.getInstance().showFpsMonitor(this);
//        decorator =FrameDecorator.getInstance(this);
//        if (!canDrawOverlays()) {
//            requestWindowPermission();
//        } else {
//            decorator.show();
//        }
    }

    private boolean canDrawOverlays() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        } else {
            return true;
        }
    }

    private void requestWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (canDrawOverlays()) {
//                decorator.show();
//            } else {
//                Toast.makeText(this, "fail to request ACTION_MANAGE_OVERLAY_PERMISSION", Toast.LENGTH_LONG).show();
//            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FpsMonitor.getInstance().hideFpsMonitor();
    }
}
