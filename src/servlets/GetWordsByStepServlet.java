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
import bean.dao.WordDao;
import bean.vo.Word;

/**
 * Servlet implementation class GetWordsByStepServlet
 */

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
		response.setContentType("text/plain;charset=utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print(3);//3表示用户未登录
		}
		else{
			String username = (String)session.getAttribute("username");
		    UserDao userdao = new UserDao();
		    String wordTable = userdao.selectWordTable(username);
		    WordDao worddao = new WordDao();
		    String wordIdStr = request.getParameter("wordid");
		    int wordid = Integer.parseInt(wordIdStr);
		    String stepStr = request.getParameter("step");
		    int step = Integer.parseInt(stepStr);
		    String type = request.getParameter("type");
		    try {
				ArrayList<Word> list = worddao.getWordsByStep(step, wordTable, wordid,type);
				boolean first = true;
				String finalStr = "";
				String tempStr;
				for(int i=0;i<list.size();i++){
					tempStr = (first?"":",")+"{"+list.get(i).toJsonString()+"}";
					first = false;
					finalStr += tempStr;
				}
				finalStr = "{\"words\":["+finalStr+"]}";
				request.setAttribute("finalStr",finalStr);
				out.print(finalStr);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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