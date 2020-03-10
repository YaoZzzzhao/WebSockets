package org.demo.service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseConn {
    @Value("${db.user}")
    private String dbUser;

    @Value("${db.pwd}")
    private String dbPwd;

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.name}")
    private String dbName;

    public Connection con = null;

    @PostConstruct
    public Connection setUpConn() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(dbUser);
            dataSource.setPassword(dbPwd);
            dataSource.setServerName(dbHost);
            dataSource.setDatabaseName(dbName);
            dataSource.setLoginTimeout(20);
            con = dataSource.getConnection();

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            return con;
        }
    }

//    public void close() {
//        try {
//            con.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        Map<String, String> map = System.getenv();
//        System.out.println(System.getProperties());
//    }
}

