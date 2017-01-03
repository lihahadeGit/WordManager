package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.dao.UserDao;
import bean.dao.WordDao;
import bean.vo.Word;

/**
 * Servlet implementation class GetWordsByStepServlet
 */
@WebServlet("/GetWordsByStepServlet")
public class GetWordsByStepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWordsByStepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String username = (String)request.getSession().getAttribute("username");
	    UserDao userdao = new UserDao();
	    String wordTable = userdao.selectWordTable(username);
	    WordDao worddao = new WordDao();
	    String date = request.getParameter("date");
	    PrintWriter out = response.getWriter();
	    try {
			ArrayList<Word> list = worddao.getWordsByStep(3, wordTable, date);
		    out.print("{'words':[{"+(String)list.get(0).toJsonString()+"},{"+(String)list.get(1).toJsonString()+"},{"+(String)list.get(2).toJsonString()+"}]}");
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}