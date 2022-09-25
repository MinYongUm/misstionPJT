package controller;

import apidto.MainApi;
import apitest.ApiJsonServer;
import apitest.TotalCnt;
import dao.WifiDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/wifi-insert.do")
public class WifiApiLoadController extends HttpServlet {

    private final ApiJsonServer apiJsonServer = new ApiJsonServer();
    private final WifiDao wifiDAO = new WifiDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TotalCnt totalCnt = new TotalCnt();

        try {
            wifiDAO.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int num = totalCnt.getNum();
        int start = 0, end = 999;

        for (int i = 0; i < num; i++) {
            MainApi mainApi = apiJsonServer.getWifiJson(start, end);

            try {
                wifiDAO.wifiInsert(mainApi.getRowApis());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            start += 1000;
            end += 1000;
        }

        req.setAttribute("totalCount", apiJsonServer.getWifiJson(0, 1).getTotalcount());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/wifiLoad.jsp");
        requestDispatcher.forward(req, res);
    }


}

