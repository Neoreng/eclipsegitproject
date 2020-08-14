package jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtil {
/*
 * 建立连接
 */
	public static Connection getConnection(){
		Connection conn = null;
		ResourceBundle rb = ResourceBundle.getBundle("jdbc.data");
		String driver = rb.getString("driver");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String pass = rb.getString("pass");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 资源的释放
	 */
	public static void release(Connection conn,PreparedStatement stmt){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection conn,PreparedStatement stmt,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
}
