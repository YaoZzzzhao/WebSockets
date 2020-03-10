package org.demo.service;

import org.demo.controller.WebSocketEndPoint;
import org.demo.model.Task;
import org.demo.model.TaskJoin;
import org.demo.model.UnionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class Client {
    private final Logger logger = LoggerFactory.getLogger(Client.class);

    @Autowired
    DatabaseConn databaseConn;

    @Autowired
    WebSocketEndPoint webSocketEndPoint;

//    public static void main(String[] args) {
//        Client client = new Client();
//        System.out.println(client.getTotal());
//    }

    public List<UnionResult> getUnionResult() {
        String sql = "SELECT 'Total' AS name, COUNT(*) AS orders FROM WF_TASK_ACTION\n" +
                "UNION\n" +
                "SELECT 'In Progress', COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'In Progress'\n" +
                "UNION\n" +
                "SELECT 'Completed', COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'Completed'\n" +
                "UNION\n" +
                "SELECT 'Cancelled', COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'Cancelled'\n" +
                "UNION\n" +
                "SELECT 'Null', COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS IS NULL";
        List<UnionResult> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                list.add(new UnionResult((String) rs.getObject("name"), (Long) rs.getObject("orders")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public List<TaskJoin> getDaily() {
        String sql = "SELECT TASK_ID, ACTION_NAME, NAME, WF_TASK_ACTION.CREATED_TIME AS CREATED_TIME,TASK_ACTION_STATUS AS STATUS " +
                "FROM WF_TASK_ACTION LEFT JOIN WF_TASK ON WF_TASK_ACTION.TASK_ID = WF_TASK.ID WHERE WF_TASK_ACTION.CREATED_TIME > " +
                "(SELECT DATE_SUB(NOW() ,INTERVAL CURTIME() HOUR_SECOND))";
        List<TaskJoin> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
            Long taskId;
            String actionName;
            String name;
            while (rs.next()) {
                if(rs.getObject("TASK_ID") == null)
                    taskId = 800L;
                else
                    taskId =(Long) rs.getObject("TASK_ID");
                if(rs.getObject("ACTION_NAME") == null)
                    actionName = "TemporaryActionName";
                else
                    actionName = (String) rs.getObject("ACTION_NAME");
                if(rs.getObject("NAME") == null)
                    name = "TemporaryName";
                else
                    name = (String) rs.getObject("NAME");
                list.add(new TaskJoin(taskId, actionName, name, rs.getTimestamp("CREATED_TIME").toLocalDateTime(), (String) rs.getObject("STATUS")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public List<TaskJoin> getWeekly() {
        String sql = "SELECT TASK_ID, ACTION_NAME, NAME, WF_TASK_ACTION.CREATED_TIME AS CREATED_TIME,TASK_ACTION_STATUS AS STATUS FROM WF_TASK_ACTION " +
                "LEFT JOIN WF_TASK ON WF_TASK_ACTION.TASK_ID = WF_TASK.ID " +
                "WHERE WF_TASK_ACTION.CREATED_TIME > (SELECT DATE_SUB(NOW()- CURTIME() ,INTERVAL WEEKDAY(CURDATE()) DAY))";
        List<TaskJoin> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
            Long taskId;
            String actionName;
            String name;
            while (rs.next()) {
                if(rs.getObject("TASK_ID") == null)
                    taskId = 800L;
                else
                    taskId =(Long) rs.getObject("TASK_ID");
                if(rs.getObject("ACTION_NAME") == null)
                    actionName = "TemporaryActionName";
                else
                    actionName = (String) rs.getObject("ACTION_NAME");
                if(rs.getObject("NAME") == null)
                    name = "TemporaryName";
                else
                    name = (String) rs.getObject("NAME");
                list.add(new TaskJoin(taskId, actionName, name, rs.getTimestamp("CREATED_TIME").toLocalDateTime(), (String) rs.getObject("STATUS")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public List<TaskJoin> getMonthly() {
        String sql = "SELECT TASK_ID, ACTION_NAME, NAME, WF_TASK_ACTION.CREATED_TIME AS CREATED_TIME,TASK_ACTION_STATUS AS STATUS " +
                "FROM WF_TASK_ACTION LEFT JOIN WF_TASK ON WF_TASK_ACTION.TASK_ID = WF_TASK.ID WHERE WF_TASK_ACTION.CREATED_TIME > " +
                "(SELECT DATE_SUB(NOW()-CURTIME(),INTERVAL DAYOFMONTH(NOW())-1 DAY))";
        List<TaskJoin> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
            Long taskId;
            String actionName;
            String name;
            while (rs.next()) {
                if(rs.getObject("TASK_ID") == null)
                    taskId = 800L;
                else
                    taskId =(Long) rs.getObject("TASK_ID");
                if(rs.getObject("ACTION_NAME") == null)
                    actionName = "TemporaryActionName";
                else
                    actionName = (String) rs.getObject("ACTION_NAME");
                if(rs.getObject("NAME") == null)
                    name = "TemporaryName";
                else
                    name = (String) rs.getObject("NAME");
                list.add(new TaskJoin(taskId, actionName, name, rs.getTimestamp("CREATED_TIME").toLocalDateTime(), (String) rs.getObject("STATUS")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public List<TaskJoin> getQuarterly() {
        String sql = "SELECT TASK_ID, ACTION_NAME, NAME, WF_TASK_ACTION.CREATED_TIME AS CREATED_TIME,TASK_ACTION_STATUS AS STATUS " +
                "FROM WF_TASK_ACTION LEFT JOIN WF_TASK ON WF_TASK_ACTION.TASK_ID = WF_TASK.ID WHERE WF_TASK_ACTION.CREATED_TIME > " +
                "(SELECT DATE_FORMAT(CONCAT(DATE_FORMAT(LAST_DAY(MAKEDATE(EXTRACT(YEAR FROM CURDATE()),1) + INTERVAL QUARTER(CURDATE())*3-3 MONTH),'%Y-%m-'),'01'),'%Y-%m-%d 00:00:00'))";
        List<TaskJoin> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
            Long taskId;
            String actionName;
            String name;
            while (rs.next()) {
                if(rs.getObject("TASK_ID") == null)
                    taskId = 800L;
                else
                    taskId =(Long) rs.getObject("TASK_ID");
                if(rs.getObject("ACTION_NAME") == null)
                    actionName = "TemporaryActionName";
                else
                    actionName = (String) rs.getObject("ACTION_NAME");
                if(rs.getObject("NAME") == null)
                    name = "TemporaryName";
                else
                    name = (String) rs.getObject("NAME");
                list.add(new TaskJoin(taskId, actionName, name, rs.getTimestamp("CREATED_TIME").toLocalDateTime(), (String) rs.getObject("STATUS")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public List<TaskJoin> getAnnually() {
        String sql = "SELECT TASK_ID, ACTION_NAME, NAME, WF_TASK_ACTION.CREATED_TIME AS CREATED_TIME,TASK_ACTION_STATUS AS STATUS " +
                "FROM WF_TASK_ACTION LEFT JOIN WF_TASK ON WF_TASK_ACTION.TASK_ID = WF_TASK.ID WHERE WF_TASK_ACTION.CREATED_TIME > " +
                "(SELECT DATE_SUB(NOW()-CURTIME(),INTERVAL DAYOFYEAR(NOW())-1 DAY))";
        List<TaskJoin> list = new ArrayList<>();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Long taskId;
            String actionName;
            String name;
            while (rs.next()) {
                if(rs.getObject("TASK_ID") == null)
                    taskId = 800L;
                else
                    taskId =(Long) rs.getObject("TASK_ID");
                if(rs.getObject("ACTION_NAME") == null)
                    actionName = "TemporaryActionName";
                else
                    actionName = (String) rs.getObject("ACTION_NAME");
                if(rs.getObject("NAME") == null)
                    name = "TemporaryName";
                else
                    name = (String) rs.getObject("NAME");
                list.add(new TaskJoin(taskId, actionName, name, rs.getTimestamp("CREATED_TIME").toLocalDateTime(), (String) rs.getObject("STATUS")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            logger.warn(">>>>>>>>>.");
        }
        return list;
    }

    public String getTotal() {
        String sql = "SELECT COUNT(*) FROM WF_TASK_ACTION";
//        Database database = new Database();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String tt = "0";
            while (rs.next()) {
                tt = rs.getString(1);
            }
            return tt;
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error!";
        }
    }

    public String getInProgress() {
        String sql = "SELECT COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'In Progress'";
//        Database database = new Database();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String ipr = "0";
            while (rs.next()) {
                ipr = rs.getString(1);
            }
            return ipr;
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error!";
        }
    }

    public String getCompleted() {
        String sql = "SELECT COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'Completed'";
//        Database database = new Database();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String comp = "0";
            while (rs.next()) {
                comp = rs.getString(1);
            }
            return comp;
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error!";
        }
    }

    public String getCancelled() {
        String sql = "SELECT COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS = 'Cancelled'";
//        Database database = new Database();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String cc = "0";
            while (rs.next()) {
                cc = rs.getString(1);
            }
            return cc;
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error!";
        }
    }

    public String getNull() {
        String sql = "SELECT COUNT(*) FROM WF_TASK_ACTION WHERE TASK_ACTION_STATUS IS NULL";
//        Database database = new Database();
        try {
            Connection connection = databaseConn.setUpConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            String nl = "0";
            while (rs.next()) {
                nl = rs.getString(1);
            }
            return nl;
        } catch (SQLException se) {
            se.printStackTrace();
            return "Error!";
        }
    }

    //    @Modifying
    public synchronized boolean insert(Task task) {
        String sql = "insert into WF_TASK_ACTION(CREATED_TIME,TASK_ACTION_STATUS) values (NOW(),?)";
        try {
            PreparedStatement pstmt = databaseConn.setUpConn().prepareStatement(sql);
//            pstmt.setString(1,task.getTaskName());
//            pstmt.setTime(1, java.sql.Time.valueOf(task.getLocalDateTime().toLocalTime()));
            pstmt.setString(1, task.getStatus());
            pstmt.executeUpdate();
            webSocketEndPoint.sendMessage("1");
            return true;
        } catch (Exception e) {
//            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean update(Task task) {
        String sql = "UPDATE WF_TASK_ACTION SET TASK_ACTION_STATUS=(?) WHERE TASK = (?)";
        try {
            PreparedStatement pstmt = databaseConn.setUpConn().prepareStatement(sql);
            pstmt.setString(1, task.getStatus());
            pstmt.setString(2, task.getTaskName());
            pstmt.executeUpdate();
            webSocketEndPoint.sendMessage("1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // This method is used to delete those records whose id is null;
    public synchronized boolean delete() {
        String sql = "DELETE FROM WF_TASK_ACTION WHERE ID IS NULL";
        try {
            PreparedStatement pstmt = databaseConn.setUpConn().prepareStatement(sql);
            pstmt.executeUpdate();
            webSocketEndPoint.sendMessage("1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
