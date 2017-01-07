package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.dao.UserDao;

/**
 * Servlet implementation class ExtensionLoginServlet
 */
public class ExtensionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtensionLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String inputPassword = request.getParameter("password");
		if(session != null){
			out.print("{'status':3,'username':"+username+"}");//3表示用户已登录
		}
		else{
			if(username == null || inputPassword == null){
				out.print("{'status':2,'username':"+username+"}");//2表示用户名或密码为空
			}
			else{
				UserDao userdao = new UserDao();
				String password = userdao.selectPassword(username);
				if(password.equals("-1")){
					out.print("{'status':-1,'username':"+username+"}");//-1表示在数据库中查询密码时出现错误
				}
				else if(inputPassword.equals(password)){
					HttpSession hSession = request.getSession();
					hSession.setAttribute("username",username);
					out.print("{'status':1,'username':"+username+"}");//1表示登录成功
				}
				else{
					out.print("{'status':0,'username':"+username+"}");//0表示密码不正确
				}
			}
		}
	}

}
