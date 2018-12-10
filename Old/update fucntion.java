public static void updateTable(String tablename, ArrayList<String[]> detail, ArrayList<String[]> condition) throws SQLException {
	int i = 0;
	String query = "";
	Connection conn = null;
	PreparedStatement prStmt = null;

	while(i < detail.size()) {
		if(i < detail.size()-1) {
			query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "', ";
		} else if (i == detail.size() - 1) {
			query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "'";
		} 
		
		i++;
	}
	try {
		conn = createConn();
		prStmt = conn.prepareStatement("update "+ tablename + " set " + query +" where " + condition.get(0)[0] + "='" + condition.get(0)[1]+"'");
//		String updateq = "update "+ tablename + " set " + query +" where " + condition.get(0)[0] + "='" + condition.get(0)[1]+"'";
		prStmt.executeUpdate();
/*		
		if(r==0)
			System.out.println("cannot update data");
		else
			System.out.println("successfully updated data");*/
			
	} catch (SQLException ex) {
		System.out.println(ex);
		ex.printStackTrace();
		System.err.println(ex.getMessage());
	}
	finally 
	{
		closePrStmt(prStmt);
		closeConn(conn);
	}
}
