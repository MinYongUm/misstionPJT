package controller;

import dao.HistoryDao;
import vo.HistoryVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/historySelect.do")
public class HistorySelectController extends HttpServlet {

    private final HistoryDao historyDao;

    public HistorySelectController() {
        this.historyDao = new HistoryDao();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<HistoryVo> list = historyDao.select();

        request.setAttribute("list",list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/history.jsp");
        requestDispatcher.forward(request,response);
    }
}
