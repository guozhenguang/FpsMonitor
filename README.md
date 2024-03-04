##一、FPSMonitor使用教程
###1.在项目根目录下的build.gradle文件中加入下面的代码：
    allprojects {
    	repositories {	
			maven {
            	url "http://172.20.4.71:8081/nexus/content/repositories/andorid-lib/"
			}
		}
	}

###2.在 app/build.gradle 文件中添加FPSMonitor的依赖，如：
    implementation 'com.ypfun.sdk.fps:fpsmonitor:1.0.0@aar'
    implementation group: "com.tencent.matrix", name: "matrix-android-lib", version: "2.0.8", changing: true
    implementation group: "com.tencent.matrix", name: "matrix-trace-canary", version: "2.0.8", changing: true
注意！！！

（1）因为该项目是在Matrix基础上完成的，所以这里Matrix的version固定使用`2.0.8`,不允许修改，以免新版本代码不兼容导致项目无法使用。
fpsmonitor

（2）fpsmonitor当前使用的版本是`1.0.0`，如有最新版本，可以更新。

###3.在Application文件中完成初始化：
    public class FpsMonitorApplication extends Application {
	    @Override
	    public void onCreate() {
	        super.onCreate();

			//FPSMonitor初始化代码
	        FpsMonitor.init(this);
	    }
	}
###4.你想要在哪个Activity里开启FPS监控，可以使用如下代码：
    public class TestFpsActivity extends Activity  {
		super.onCreate(savedInstanceState);
		@Override
    	protected void onCreate(@Nullable Bundle savedInstanceState) {
			//开启FPS帧率监控
			FpsMonitor.getInstance().showFpsMonitor(this);
		}

		@Override
	    protected void onDestroy() {
	        super.onDestroy();
			//Activity退出时，要关掉
	        FpsMonitor.getInstance().hideFpsMonitor();
	    }
	}


----------
##二、FPS帧率监控界面分析
敬请期待......