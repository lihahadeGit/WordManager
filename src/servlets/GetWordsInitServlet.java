package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.setContentType("text/plain;charset=utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print("{\"type\":3}");//3表示用户未登录
		}
		else{
			String username = (String)session.getAttribute("username");
			UserDao  userdao = new UserDao();
			String wordTable = userdao.selectWordTable(username);
			WordDao worddao = new WordDao();
			String browseWay = request.getParameter("browseWay");
			if(browseWay != null){
				ArrayList<Word> wordlist;
				if(browseWay.equals("browseByDate")){
				    wordlist = worddao.getWord(wordTable);
				}
				else if(browseWay.equals("browseByAlphabet")){
					wordlist = worddao.getAllWord(wordTable);
				}
				else{
					wordlist = null;
				}
				if(wordlist != null){
					boolean first = true;
					String finalStr = "";
					String tempStr;
					for(int i=0;i<wordlist.size();i++){
						tempStr = (first?"":",")+"{"+wordlist.get(i).toJsonString()+"}";
						first = false;
						finalStr += tempStr;
					}
					finalStr = "{\"words\":["+finalStr+"],\"type\":1}";//执行成功
					out.print(finalStr);
				}else{
					//设置响应状态码为500
					out.print("{\"type\":-1}");//出错了
				}
			}
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
