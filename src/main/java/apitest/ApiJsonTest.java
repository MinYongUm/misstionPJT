package apitest;

import apidto.MainApi;
import apidto.RowApi;

import java.io.IOException;

public class ApiJsonTest {
    public static void main(String[] args) throws IOException {
        ApiJsonServer apiJsonServer = new ApiJsonServer();

        MainApi mainApi = apiJsonServer.getWifiJson(1, 100);

        System.out.println("getResult= " + mainApi.getResult());
        System.out.println("===");
        for (RowApi rowApi : mainApi.getRowApis()) {
            System.out.println("MainName= " + rowApi.getMainNm());
            System.out.println("Dttm= " + rowApi.getDttm());
        }
        System.out.println("===");
        System.out.println("TotalCount= " + mainApi.getTotalcount());
    }






}
