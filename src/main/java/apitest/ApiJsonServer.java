package apitest;

import apidto.MainApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class ApiJsonServer {

    public MainApi getWifiJson(int start, int end) throws IOException {
        String url = "http://openapi.seoul.go.kr:8088/564f53684c6d696e35315454457061/json/TbPublicWifiInfo/"
                + start + "/" + end;

        OkHttpClient okHttpClient = new OkHttpClient();
        URL urlRequst = new URL(url);
        Request request = new Request.Builder().url(urlRequst).get().build();

        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();

        ApiParser apiParser = new ApiParser();

        return apiParser.parse(json);

    }

}
