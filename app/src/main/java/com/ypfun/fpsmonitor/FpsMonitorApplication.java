package com.ypfun.fpsmonitor;

import android.app.Application;


import com.ypfun.fpslibrary.FpsMonitor;
import com.ypfun.fpslibrary.MatrixPluginListener;


/**
 * @author guozhenguang
 * @date 2024/3/1
 * @description
 */
public class FpsMonitorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FpsMonitor.init(this);

//        Matrix.Builder builder = new Matrix.Builder(this);
//        builder.pluginListener(new MatrixPluginListener(this));
//        TraceConfig traceConfig = new TraceConfig.Builder()
//                .enableFPS(true)
//                .isDebug(true)
//                .isDevEnv(false)
//                .build();
//        TracePlugin tracePlugin = (new TracePlugin(traceConfig));
//        builder.plugin(tracePlugin);
//
//        Matrix.init(builder.build());
//        tracePlugin.start();
    }
}
