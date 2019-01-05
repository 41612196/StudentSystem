package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create by pengweijie on 2018/11/17
 */
public class DataAccess {
    public static Connection getConnection() {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccs?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
        } catch (ClassNotFoundException e) {
            System.out.println("获取connection时找不到类文件");
        } catch (SQLException e) {
            System.out.println("获取connection时SQL异常");
        }
        return conn;
    }
}
