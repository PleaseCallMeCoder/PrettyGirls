package coder.prettygirls.app.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;

/**
 * 系统异常处理类
 * @author PLUTO
 *
 */
public abstract class BaseExceptionHandler implements UncaughtExceptionHandler {
	
	public static final String TAG = "CrashHandler";
	
	protected static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);

	/**
	 * 未捕获异常跳转
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		//如果正确处理的为捕获异常
		if (handleException(ex)) {
			try {
				//如果处理了,让程序继续运行3秒后退出,保证错误日志的保存
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}
	}

	/**
	 * 自定义错误处理,手机错误信息,发送错误报告操作均在此完成,开发者可以根据自己的情况来自定义异常处理逻辑
	 * @param ex
	 * @return
	 */
	public abstract boolean handleException(Throwable ex);
}
