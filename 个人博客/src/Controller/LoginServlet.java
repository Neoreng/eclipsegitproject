package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import entity.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		String tel=request.getParameter("account");
		String name=request.getParameter("account");
		String password=request.getParameter("password");
		User user=new User(name,tel,password);
		int result=UserDao.login(user);
		if(result>0) {
			//登陆成功
			String username=UserDao.find(name);
			HttpSession session=request.getSession();
			session.setAttribute("username",username);
			session.setAttribute("password", password);
			response.sendRedirect("selfpage.jsp");
		}
		else {
			//登录失败
			response.sendRedirect("login.jsp?error=1");
		}
	}

}
