package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.dao.UserDao;

/**
 * Servlet implementation class GetAllGlossaryNameServlet
 */
public class GetAllGlossaryNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllGlossaryNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print("{\"type\":3}");//用户未登录
		}
		else{
			String username = (String)session.getAttribute("username");
			UserDao userdao = new UserDao();
			ArrayList<String>  list= userdao.selectGlossaryInfoFromUser(username);
			if(list == null){
				out.print("{\"type\":-1}");//查询glossaryinfo信息时出错
			}
			else{
				if(list.size() == 2){
					String oldGlossaryName = list.get(0);
					out.print("{\"oldGlossaryName\":\""+oldGlossaryName+"\",\"type\":1}");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
