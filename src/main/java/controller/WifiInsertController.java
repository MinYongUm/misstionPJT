package controller;

import apitest.ApiJsonServer;
import dao.WifiDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifiInsert.do")
public class WifiInsertController extends HttpServlet {

    ApiJsonServer apiJsonServer = new ApiJsonServer();
    WifiDao wifiDAO = new WifiDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
