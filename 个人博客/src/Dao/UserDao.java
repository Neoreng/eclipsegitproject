package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import jdbc.JdbcUtil;
//hahha
public class UserDao {
	public static String find(String name) {
		String result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = JdbcUtil.getConnection();
		String sql = "select name from user where name=? or tel=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			if(rs.next()){
				result=rs.getString(1);
			}
			if(result!=null) {
				return result;
			}else {
				return null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
	}
	public static int login(User user){
		int result=-1;
		int flag=-1;//-1表示系统异常，0表示用户名或密码错误，1表示登录成功
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = JdbcUtil.getConnection();
		String sql = "select count(*) from user where (name=? or tel=?) and password=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getTel());
			stmt.setString(3, user.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
			if(result>0) {
				return 1;
			}else {
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
	}

	public static int regist(User user) {
		int result=-1;
		int flag=-1;//-1表示系统异常，0表示用户名或密码错误，1表示登录成功
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs =0;
		conn = JdbcUtil.getConnection();
		String sql = "insert into user(name,tel,sex,password) values(?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getTel());
			stmt.setString(4, user.getPassword());
			stmt.setString(3, user.getSex());
			rs = stmt.executeUpdate();
			if(rs>0) {
				return 1;
			}else {
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.release(conn,stmt);
		}
	}
}
