package controller;

import dao.HistoryDao;
import dao.WifiDao;
import vo.WifiVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/wifiNear.do")
public class WifiNearController extends HttpServlet {

    WifiDao wifiDao = new WifiDao();
    HistoryDao historyDao = new HistoryDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Double lat = Double.valueOf(request.getParameter("lat"));
        Double lnt = Double.valueOf(request.getParameter("lnt"));

        request.setAttribute("lat",lat);
        request.setAttribute("lnt",lnt);

        List<WifiVo> searchList = wifiDao.search(lat, lnt);
        try {
            historyDao.save(lat,lnt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("searchList",searchList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
        requestDispatcher.forward(request,response);
    }
}
