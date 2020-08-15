package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Blog;
import jdbc.JdbcUtil;
//hello
public class BlogDao {
	public static int publish(String title,String author,String type,String content) {
		int result=-1;
		int flag=-1;//-1表示系统异常，0表示用户名或密码错误，1表示登录成功
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs =0;
		conn = JdbcUtil.getConnection();
		String sql = "insert into blog(title,author,type,content) values(?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, type);
			stmt.setString(4, content);
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
	public ArrayList<Blog> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from blog";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("title"));
				blog.setType(rs.getString("type"));
				blog.setAuthor(rs.getString("author"));
				blog.setLike_num(rs.getInt("like_num"));
				blog.setTime(rs.getString("time"));
				blog.setContent(rs.getString("content"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
	public ArrayList<Blog> own(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from blog where author=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("title"));
				blog.setType(rs.getString("type"));
				blog.setAuthor(rs.getString("author"));
				blog.setLike_num(rs.getInt("like_num"));
				blog.setTime(rs.getString("time"));
				blog.setContent(rs.getString("content"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
	public static void like(int blog_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = JdbcUtil.getConnection();
		String sql = "update blog set like_num=like_num+1 where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt);
		}		
	}
	public static void notlike(int blog_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = JdbcUtil.getConnection();
		String sql = "update blog set like_num=like_num-1 where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt);
		}		
	}
	public ArrayList<Blog> findOne(int blog_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from blog where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("title"));
				blog.setType(rs.getString("type"));
				blog.setAuthor(rs.getString("author"));
				blog.setLike_num(rs.getInt("like_num"));
				blog.setTime(rs.getString("time"));
				blog.setContent(rs.getString("content"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
	public ArrayList<Blog> comment(int blog_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from comment where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setCom_name(rs.getString("com_name"));
				blog.setComment(rs.getString("comment"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
	public static int addcomment(int blog_id,String comment, String com_name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = JdbcUtil.getConnection();
		String sql = "insert into comment(blog_id,comment,com_name) values(?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			stmt.setString(2, comment);
			stmt.setString(3, com_name);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt);
		}
		return 1;
	}
	public static ArrayList<Blog> type(String type) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from blog where type=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("title"));
				blog.setType(rs.getString("type"));
				blog.setAuthor(rs.getString("author"));
				blog.setLike_num(rs.getInt("like_num"));
				blog.setTime(rs.getString("time"));
				blog.setContent(rs.getString("content"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
	public static int delboke(int blog_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		int rs = 0;
		conn = JdbcUtil.getConnection();
		String sql = "delete from blog where blog_id=?";
		String sql2 = "delete from comment where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blog_id);
			rs=stmt.executeUpdate();
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, blog_id);
			rs=stmt2.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt);
		}
		if(rs!=0) {
			return 1;
		}
		return 0;
	}
	public static int change(int blog_id, String title, String author, String type, String content) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs=0;
		conn = JdbcUtil.getConnection();
		String sql = "update blog set title=?,author=?,type=?,content=? where blog_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(5, blog_id);
			stmt.setString(1,title);
			stmt.setString(2,author);
			stmt.setString(3,type);
			stmt.setString(4,content);
			rs = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt);
		}
		if(rs>0) {
			return 1;
		}else {
			return 0;
		}
	}
	public static ArrayList<Blog> title(String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Blog> list = new ArrayList<Blog>();
		conn = JdbcUtil.getConnection();
		String sql = "select * from blog where title like '%"+title+"%'";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Blog blog=new Blog();
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setTitle(rs.getString("title"));
				blog.setType(rs.getString("type"));
				blog.setAuthor(rs.getString("author"));
				blog.setLike_num(rs.getInt("like_num"));
				blog.setTime(rs.getString("time"));
				blog.setContent(rs.getString("content"));
				list.add(blog);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(conn,stmt,rs);
		}
		return list;
	}
}
