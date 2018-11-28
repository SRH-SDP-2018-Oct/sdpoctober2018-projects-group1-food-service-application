package org.srh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RootCon {

    private static final String URL = "jdbc:mysql://localhost:3306/mealsanddeals";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver"; 

    static {
        try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
    }


	public static Connection createConn() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}


	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				if(!conn.isClosed())
					conn.close();
			} catch (SQLException ex) {
				System.out.println("Connection is null!");
				ex.printStackTrace();
			}
		}
	}


	public static void closePrStmt(PreparedStatement prStmt) {
		if(prStmt!=null) {
			try {
				if(!prStmt.isClosed())
					prStmt.close();
			} catch (SQLException ex) {
				System.out.println("PreparedStatement is null!");
				ex.printStackTrace();
			}
		}
	}


	public static void closeRs(ResultSet rs) {
		if(rs!=null) {
			try {
				if(!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet is null!");
				e.printStackTrace();
			}
		}
	}
}
