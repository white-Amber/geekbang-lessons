package cn.yz.okhttp.learn;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Description
 * @Date 2022/6/17
 * @Author yuze
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .build();
        Request request = new Request.Builder()
                .url("http://www.jianshu.com/")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
        System.out.println(response.headers());


    }

    public static void getDemo() throws IOException {
        // 1. 拿到OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2. 构造Request对象
        Request request = new Request.Builder()
                .get()
                .url("https:www.baidu.com")
                .build();
        // 3. 将Request封装为Call
        Call call = client.newCall(request);
        // 4. 根据需要调用同步或异步请求。
        Response response = call.execute();
        System.out.println(response);
        System.out.println(response.headers());
    }


}
