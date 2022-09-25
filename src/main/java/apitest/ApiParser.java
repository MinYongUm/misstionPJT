package apitest;

import apidto.BasicApi;
import apidto.MainApi;
import com.google.gson.Gson;

public class ApiParser {

    Gson gson = new Gson();

    public MainApi parse(String json) {
        BasicApi basicApi = gson.fromJson(json, BasicApi.class);

        return basicApi.getTbPublicWifiInfo();
    }
}
