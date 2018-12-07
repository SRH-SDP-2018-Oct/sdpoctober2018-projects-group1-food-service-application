package Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//make notification list 
public class GetNotificationList extends MysqlCon{
	
	
	public void GetAllNotificationList(ArrayList<Notification> notificationlist) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = createConn();
			String sql = "select * from notification ";//¿¹Á¦ 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
						
			while(rs.next()) {
				    Notification newNotification = new Notification();
					newNotification.setNotificationid(rs.getInt("notificationid"));
					newNotification.setSenderusername(rs.getString("senderusername"));
					newNotification.setReceiverusername(rs.getString("receiverusername"));
					newNotification.setSubject(rs.getString("subject"));
					newNotification.setContent(rs.getString("content"));
					newNotification.setSenddate(rs.getDate("senddate"));
					newNotification.setStatus(rs.getString("status"));
					notificationlist.add(newNotification);
		
			}
			con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


