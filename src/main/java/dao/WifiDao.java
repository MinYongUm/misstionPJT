package dao;

import apidto.RowApi;
import vo.WifiVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {
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

    public void wifiInsert(List<RowApi> list) throws SQLException {
        String SQL = "insert into wifi(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        getConnection();
        connection.setAutoCommit(false);
        try {
            preparedStatement = connection.prepareStatement(SQL);
            int batchSize = 1000;
            int cnt = 0;
            for (int i = 0; i < list.size(); i++) {
                RowApi rowApi = list.get(i);

                preparedStatement.setString(1, rowApi.getMgrNo());
                preparedStatement.setString(2, rowApi.getWrdofc());
                preparedStatement.setString(3, rowApi.getMainNm());
                preparedStatement.setString(4, rowApi.getAdres1());
                preparedStatement.setString(5, rowApi.getAdres2());
                preparedStatement.setString(6, rowApi.getFloor());
                preparedStatement.setString(7, rowApi.getTy());
                preparedStatement.setString(8, rowApi.getMby());
                preparedStatement.setString(9, rowApi.getSvcSe());
                preparedStatement.setString(10, rowApi.getCmcwr());
                preparedStatement.setString(11, rowApi.getYear());
                preparedStatement.setString(12, rowApi.getDoor());
                preparedStatement.setString(13, rowApi.getRemars3());
                preparedStatement.setString(14, rowApi.getLat());
                preparedStatement.setString(15, rowApi.getLnt());
                preparedStatement.setString(16, rowApi.getDttm());

                preparedStatement.addBatch();
                cnt++;

                if (batchSize == cnt) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    connection.commit();
                    cnt = 0;
                }
            }
            preparedStatement.executeBatch();
            connection.commit();


        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            DbClose();
        }
    }

    public void deleteAll() throws SQLException {
        String sql = "truncate table wifi";

        getConnection();
        connection.setAutoCommit(false);

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
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

    public List<WifiVo> search(Double lat, Double lnt) {
        String SQL = "select * " +
                ", format((6371 * acos(cos(radians(" + lat + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + lnt + ")) " +
                "+ sin(radians(" + lat + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            List<WifiVo> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new WifiVo(
                        resultSet.getString("distance"),
                        resultSet.getString("X_SWIFI_MGR_NO"),
                        resultSet.getString("X_SWIFI_WRDOFC"),
                        resultSet.getString("X_SWIFI_MAIN_NM"),
                        resultSet.getString("X_SWIFI_ADRES1"),
                        resultSet.getString("X_SWIFI_ADRES2"),
                        resultSet.getString("X_SWIFI_INSTL_FLOOR"),
                        resultSet.getString("X_SWIFI_INSTL_TY"),
                        resultSet.getString("X_SWIFI_INSTL_MBY"),
                        resultSet.getString("X_SWIFI_SVC_SE"),
                        resultSet.getString("X_SWIFI_CMCWR"),
                        resultSet.getString("X_SWIFI_CNSTC_YEAR"),
                        resultSet.getString("X_SWIFI_INOUT_DOOR"),
                        resultSet.getString("X_SWIFI_REMARS3"),
                        resultSet.getString("LAT"),
                        resultSet.getString("LNT"),
                        resultSet.getString("WORK_DTTM")
                ));
            }
            return list;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbClose();
        }
    }
}
