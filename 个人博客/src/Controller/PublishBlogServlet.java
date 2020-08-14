package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BlogDao;

@WebServlet("/PublishBlogServlet")
public class PublishBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		//PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		int result=BlogDao.publish(title,author,type,content);
		if(result>0) {
			//发表成功
			response.sendRedirect("selfpage.jsp");
		}
		else {
			//发表失败
			response.sendRedirect("publish.jsp?error=0");
		}
	}

}
