package coder.prettygirls.app.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import coder.prettygirls.util.FileUtil;

/**
 * 本地异常处理类
 *
 * @author PLUTO
 */
public class LocalFileHandler extends BaseExceptionHandler {

    private Context context;

    public LocalFileHandler(Context context) {
        this.context = context;
    }

    /**
     * 自定义错误处理,手机错误信息,发送错误报告操作均在此完成,开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return
     */
    @Override
    public boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉，程序出现异常，正在从错误中恢复", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        //保存错误日志
        saveLog(ex);

        return true;
    }

    /**
     * 保存错误日志到本地
     *
     * @param ex
     */
    private void saveLog(Throwable ex) {
        try {

            File path = new File(FileUtil.getDiskCacheDir(context) + "/log");
            if (!path.exists()) {
                path.mkdirs();
            }

            File errorFile = new File(path + "/crash.txt");

            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }

            OutputStream out = new FileOutputStream(errorFile, true);
            out.write(("\n\n-----错误分割线" + dateFormat.format(new Date()) + "-----\n\n").getBytes());
            PrintStream stream = new PrintStream(out);
            ex.printStackTrace(stream);
            stream.flush();
            out.flush();
            stream.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
