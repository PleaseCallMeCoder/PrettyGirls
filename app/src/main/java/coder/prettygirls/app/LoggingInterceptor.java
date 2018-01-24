package coder.prettygirls.app;

import java.io.IOException;

import coder.prettygirls.util.LogUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        LogUtil.d(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(),
                request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        LogUtil.d(String.format("Received response for %s in %.1fms%n%s", response.request().url(),
                (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}