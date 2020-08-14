package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BlogDao;
import entity.Blog;
@WebServlet("/TitleServlet")
public class TitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		//PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		ArrayList<Blog> blog=BlogDao.title(title);
		request.setAttribute("blog", blog);
		request.getRequestDispatcher("community.jsp").forward(request, response);
	}

}
