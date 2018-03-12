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
import bean.dao.WordDao;

/**
 * Servlet implementation class DeleteWordServlet
 */
//用于浏览的时候删除
public class DeleteWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWordServlet() {
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
			out.print(3);//3表示用户未登录
		}
		else{
			String username = (String)session.getAttribute("username");
			String wordId = request.getParameter("wordId");
			UserDao userdao = new UserDao();
			String wordTable = userdao.selectWordTable(username);
			if(wordTable.equals("-1") || wordTable == null){
				out.print(2);//查询wordTable的过程中出错
			}
			else{
				WordDao worddao = new WordDao();
				int returnValue = worddao.deleteWord(wordTable, wordId);
				out.print(returnValue);//0表示不存在该wordid,-1表示出错，1表示操作成功
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
