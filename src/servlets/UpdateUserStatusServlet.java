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
 * Servlet implementation class UpdateUserStatus
 */
public class UpdateUserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserStatusServlet() {
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
		response.setContentType("text/utf-8;charset=utf-8");
		String checkOrUpdate = request.getParameter("checkOrUpdate");
		String inputPassword = request.getParameter("password");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserDao userdao = new UserDao();
		String username = (String)session.getAttribute("username");
		int returnValue;
		if(checkOrUpdate != null){
			String password = userdao.selectPassword(username);
			if(checkOrUpdate.equals("check")){
				if(password.equals("-1")){
					out.print(-1);//-1密码查找过程中数据库出错
				}
				else{
					if(inputPassword.equals(password)){
						returnValue = 1;
						out.print(returnValue);//1表示密码输入正确
						
					}else{
						returnValue = 0;
						out.print(returnValue);//0表示密码输入错误
					}
				}
			}
			else if(checkOrUpdate.equals("update")){
				String password1 = request.getParameter("password1");
				String password2 = request.getParameter("password2");
				if(password1.equals(password2)){
					returnValue = userdao.updateUser(username,password);
					out.print(returnValue);//-1表示更新数据库过程中出现错误，1表示操作成功
				}
				else{
					returnValue = 0;
					out.print(returnValue);//0表示两次输入的密码不一样
				}
			}

		}	
	}
}
