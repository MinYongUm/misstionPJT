package apitest;

import apidto.MainApi;

import java.io.IOException;

public class TotalCnt {

    public int getNum() throws IOException {
        ApiJsonServer apiJsonServer = new ApiJsonServer();
        MainApi wifiJson = apiJsonServer.getWifiJson(1, 100);

        int totalCount = wifiJson.getTotalcount();
        int num = totalCount / 1000;
        if (totalCount > num * 1000) {
            num++;
        }
        return num;
    }
}
