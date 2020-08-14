package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BlogDao;

@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		//PrintWriter out=response.getWriter();
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		int result=BlogDao.change(blog_id,title,author,type,content);
		if(result>0) {
			//成功
			response.sendRedirect("selfpage.jsp");
		}
		else {
			//失败
			request.setAttribute("blog_id", blog_id);
			request.getRequestDispatcher("xiugai.jsp?error=0").forward(request, response);
		}
	}

}
