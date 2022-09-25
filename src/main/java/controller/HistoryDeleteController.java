package controller;

import dao.HistoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/historyDelete.do")
public class HistoryDeleteController extends HttpServlet {

    private final HistoryDao historyDao;

    public HistoryDeleteController(){
        this.historyDao = new HistoryDao();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter("deleteId"));

        try {
            historyDao.deleteById(deleteId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
