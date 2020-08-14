package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.BlogDao;


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
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
		String com_name=request.getParameter("com_name");
		String comment=request.getParameter("comment");
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int result=BlogDao.addcomment(blog_id,comment,com_name);
		if(result>0) {
			//成功
			request.setAttribute("blog_id", blog_id);
			request.getRequestDispatcher("operation.jsp").forward(request, response);
		}
	}


}
