package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import bean.dao.UserDao;
import bean.dao.WordDao;
import bean.vo.Word;

/**
 * Servlet implementation class getWordsInitServlet
 */

public class GetWordsInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWordsInitServlet() {
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
		UserDao  userdao = new UserDao();
		String wordTable = userdao.selectWordTable(username);
		WordDao worddao = new WordDao();
		ArrayList<Word> wordlist = worddao.getWord(wordTable);
		PrintWriter out = response.getWriter();
		if(wordlist != null){
			request.setAttribute("wordlist",wordlist);
		}else{
			//设置响应状态码为500
			response.sendError(500,"出错了");
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
