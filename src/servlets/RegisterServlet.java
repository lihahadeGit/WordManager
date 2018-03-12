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
 * Servlet implementation class registerServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		String username = request.getParameter("username");
		UserDao userdao = new UserDao();
		int existUser = userdao.selectUser(username);
		PrintWriter out = response.getWriter();
		out.print(existUser);//0表示用户名未被注册，-1表示查询失败，1表示用户名已被注册
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userdao = new UserDao();
		PrintWriter out = response.getWriter();
		int existUser = userdao.selectUser(username);
		if(existUser == 0){
			int insertResult;
			insertResult = userdao.insertUser(username, password);
			//System.out.println(insertResult);
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			//session.setAttribute("password",password );
			out.print(insertResult);//-1表示失败，1表示成功
		}else{
			out.print(existUser);//1表示该用户名已被注册，-1表示查询失败
		}
		
	}

}
