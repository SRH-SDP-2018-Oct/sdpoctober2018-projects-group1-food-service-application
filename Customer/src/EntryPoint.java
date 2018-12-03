import java.sql.SQLException;

public class EntryPoint {
	static CustomerMain mainStart = new CustomerMain();
	public static void main(String [] args) {
		try {
			MysqlCon.createConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainStart.main();
	}

}
