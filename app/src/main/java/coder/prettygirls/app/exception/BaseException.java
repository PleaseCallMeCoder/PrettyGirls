package coder.prettygirls.app.exception;

/**
 * 自定义异常,系统中所有异常都转化为Exceptions
 * @author PLUTO
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(String detailMessage) {
		super(detailMessage);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}

	public BaseException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
