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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		UserDao userdao = new UserDao();
		int existUser = userdao.selectUser(username);
		PrintWriter out = response.getWriter();
		out.print(existUser);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		System.out.println(session == null?"null":"1");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String inputPassword = request.getParameter("password");
		if(session != null){
			if(session.getAttribute("username") != null&&session.getAttribute("username").equals(username)){
				out.print(3);//已登录，跳转到主页面
			}
			else{
				UserDao userdao = new UserDao();
				String password = userdao.selectPassword(username);
				if(password.equals("-1")){
					out.print(-1);//数据库查找出错
				}
				else{
					if(inputPassword.equals(password)){
						out.print(1);//登录成功
						session.setAttribute("username",username);
					}else{
						out.print(0);//密码不正确
					}
				}
			}
		}
		else{
			HttpSession hsession = request.getSession();
			UserDao userdao = new UserDao();
			String password = userdao.selectPassword(username);
			if(password.equals("-1")){
				out.print(-1);//数据库查找出错
			}
			if(inputPassword.equals(password)){
				out.print(1);//登录成功
				hsession.setAttribute("username",username);
			}else{
				out.print(0);//密码不正确
			}
		}
		
	}

}

