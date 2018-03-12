package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.dao.UserDao;
import bean.dao.WordDao;

/**
 * Servlet implementation class SaveWordServlet
 */
public class ExtensionSaveWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtensionSaveWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //-1表示在对数据库的操作中发生异常，0表示对数据库的操作未得到想要的结果，1表示操作成功
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null||session.getAttribute("username") == null){
			out.print(2);//session为空状态码是2,用户未登陆
		}
		else{
			System.out.println( request.getParameter("text_Chinese"));
			String username = (String)session.getAttribute("username");
			UserDao userdao = new UserDao();
			WordDao worddao = new WordDao();
			String wordTableName = userdao.selectWordTable(username);
			String test_Chinese = request.getParameter("text_Chinese");
			String test_English = request.getParameter("text_English");
			String alphabetUK = request.getParameter("alphabetUK");
			String alphabetUS = request.getParameter("alphabetUS");
			int reciteFlag = 0;
			int reciteNum = 1;
			Date addTime = new Date();
			String resourceUrl = request.getParameter("resourceUrl");
			int returnValue;
			if(wordTableName.equals("-1")){
				out.print(-1);
			}else{
				returnValue = worddao.insertWord(wordTableName, test_Chinese,
						test_English,alphabetUK,alphabetUS,reciteFlag,
						reciteNum,addTime,resourceUrl);
				if(returnValue == -1){
					out.print(-1);
				}else{
					out.print(1);
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
