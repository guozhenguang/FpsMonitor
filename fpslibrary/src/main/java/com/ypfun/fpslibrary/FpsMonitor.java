package com.ypfun.fpslibrary;

import android.app.Application;
import android.content.Context;

import com.tencent.matrix.Matrix;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.config.TraceConfig;

/**
 * @author guozhenguang
 * @date 2024/3/4
 * @description
 */
public class FpsMonitor {
    private final String TAG = "FpsMonitor";

    private static volatile FpsMonitor instance;
    private FrameDecorator decorator;

    public static void init(Application application) {
        if (instance == null){
            synchronized (FpsMonitor.class){
                if (instance == null){
                    instance = new FpsMonitor(application);
                }
            }
        }
    }

    private FpsMonitor(Application application){
        Matrix.Builder builder = new Matrix.Builder(application);
        builder.pluginListener(new MatrixPluginListener(application));
        TraceConfig traceConfig = new TraceConfig.Builder()
                .enableFPS(true)
                .isDebug(true)
                .isDevEnv(false)
                .build();
        TracePlugin tracePlugin = (new TracePlugin(traceConfig));
        builder.plugin(tracePlugin);

        Matrix.init(builder.build());
        tracePlugin.start();
    }

    public static FpsMonitor getInstance() { return instance; }

    public void showFpsMonitor(Context context){
        if (decorator == null){
            decorator = FrameDecorator.getInstance(context);
        }
        decorator.show();
    }

    public void hideFpsMonitor(){
        if (decorator != null){
            decorator.dismiss();
        }
    }
}
