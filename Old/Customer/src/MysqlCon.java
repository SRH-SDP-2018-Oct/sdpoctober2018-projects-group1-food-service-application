

import java.sql.*;
import java.util.*;
//import com.srh_heidelberg.mealsanddeals.AdminHomePage;



public class MysqlCon{
 
	private static final String URL = "jdbc:mysql://localhost:3306/mealsanddeals";
    private static final String USER = "root";
    private static final String PASSWORD = "26.Null4&1%";
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
	public static void insertToTable(String tablename , ArrayList<String> detail) {
		
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;

		
		int i = 0;
		
		String query = "";
		
		while(i < detail.size()) {

			if(i < detail.size()-1) {

				query = query + "'" + detail.get(i) + "', ";

			} else if (i == detail.size() - 1) {

				query = query + "'" + detail.get(i) + "'";

			}

			i++;
		}
			try {
				conn = createConn();
				prStmt = conn.prepareStatement("insert into "+ tablename +" values ("+query+")");
				prStmt.executeUpdate();
				
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage());
			} finally {
				closeRs(rs);
				closePrStmt(prStmt);
				closeConn(conn);
			}

	}

	public static void login(String tablename , ArrayList<String> detail){

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		String tablename1 = null;
		tablename1 = tablename.toLowerCase();
		String query = null;
		String username = null;

		if(tablename1.equals("admin")) 
		{
				query = "select * from "+tablename1+" where username='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
				username = "username";
			} else if(tablename1.equals("fsa")) {
				query = "select * from "+tablename1+" where fsausername='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
				username = "fsausername";
			} else if(tablename1.equals("customer")) {
				query = "select * from "+tablename1+" where customerusername='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
				username = "customerusername";
		}

		try{  

			conn = createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();

			if(rs.next()) {
				if(tablename1.equals("fsa")) 
				{		
					if(rs.getString("validation").equals("Active")) 
					{
						System.out.print(tablename1.toUpperCase()+"  "+rs.getString(username)+" Logged IN");
					} 
					else
					{
						System.out.print("Your Account is not Active yet..");
					}
				} 
				else if(tablename1.equals("customer")) 
				{
					System.out.print(tablename1.toUpperCase()+"  "+rs.getString(username)+" Logged IN");
				} 
				else if(tablename1.equals("admin"))
				{
//					AdminHomePage.homePage();
				}
			}
			else if(!rs.next()) 
			{
				System.out.print("Username or Password is not Correct");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} 
		finally 
		{
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
		}
	}
}
/*    
    
    (String tablename , ArrayList<String> detail) throws SQLException 
    public static void tableSelect(String tablename){  
		try{  
			Class.forName(DRIVER_CLASS);  
			Connection con = DriverManager.getConnection(  
			"jdbc:mysql://localhost:33061/mealsanddeals","root","Foodserviceapplication1@");
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from "+tablename);
			int i = 1;
			while(rs.next()) {
				while (i <= rs.getMetaData().getColumnCount()) {
					System.out.print(rs.getString(i)+"   ");
				i++;
				}
			}
				con.close();
				stmt.close();
				rs.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
				try {
				conn = createConn();
				prStmt = conn.prepareStatement("select * from "+tablename);
				prStmt.executeUpdate();
				
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage());
			} finally {
				closeRs(rs);
				closePrStmt(prStmt);
				closeConn(conn);
			}

	*/
//}
