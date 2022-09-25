package dao;

import vo.HistoryVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    void getConnection() {
        String URL = "jdbc:mysql://localhost/kobis_db";
        String id = "root";
        String pass = "qwer1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, id, pass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int save(Double lat, Double lnt) throws SQLException {
        String SQL = "Insert into history(lat, lnt, date) values(?,?,now())";
        getConnection();
        int cnt = -1;

        try {
            preparedStatement = connection.prepareStatement(SQL);

            connection.setAutoCommit(false);
            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lnt);
            cnt = preparedStatement.executeUpdate();

            connection.commit();
            return cnt;

        } catch (Exception e) {
            connection.rollback();
            connection.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }

    }

    public void deleteById(int num) throws SQLException {
        String SQL = "Delete from history where id = ?";
        getConnection();
        connection.setAutoCommit(false);

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, num);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }

    public List<HistoryVo> select() {
        String SQL = "select * from history order by id desc";
        List<HistoryVo> list = new ArrayList<>();
        getConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new HistoryVo(
                        resultSet.getString("id"),
                        resultSet.getDouble("lat"),
                        resultSet.getDouble("lnt"),
                        resultSet.getString("date")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }

    }


    public void DbClose() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
