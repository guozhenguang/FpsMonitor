package com.ypfun.fpslibrary;

import android.content.Context;
import android.os.Environment;

import com.tencent.matrix.plugin.DefaultPluginListener;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.util.MatrixLog;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guozhenguang
 * @date 2024/2/8
 * @description
 */
public class MatrixPluginListener extends DefaultPluginListener {
    public static final String TAG = "MatrixPluginListener";
    public MatrixPluginListener(Context context) {
        super(context);
    }

    @Override
    public void onReportIssue(Issue issue) {
        super.onReportIssue(issue);
        String key = "stack";
//        if (issue.getContent().has(key)) {
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//                File methodFilePath = new File(Environment.getExternalStorageDirectory(),
//                        sdf.format(new Date()) + ".txt");
//                String stack = issue.getContent().getString(key);
//                FileWriter fileWriter = new FileWriter(methodFilePath);
//                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                bufferedWriter.write(stack);
//                bufferedWriter.close();
//            } catch (JSONException | IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
