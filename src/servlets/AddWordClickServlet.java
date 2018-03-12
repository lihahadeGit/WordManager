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

import bean.dao.GlossaryDao;
import bean.dao.UserDao;
import bean.vo.Glossary;

/**
 * Servlet implementation class AddWordClickServlet
 */
public class AddWordClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWordClickServlet() {
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
		String username = (String)session.getAttribute("username");
		UserDao userdao = new UserDao();
		ArrayList<String>  list= userdao.selectGlossaryInfoFromUser(username);
		int wordId = Integer.parseInt(request.getParameter("wordid"));
		if(list != null){
			if(list.size() == 2){
				String oldGlossaryName = list.get(0);
				String oldGlossaryNameInternal = list.get(1);
				String[] gn = oldGlossaryName.split(",");
				String[] gni = oldGlossaryNameInternal.split(",");
				ArrayList<String> gnlist = new ArrayList<String>();
				for(int i = 0;i<gn.length;i++){
					GlossaryDao glossarydao = new GlossaryDao();
					Glossary glossary = glossarydao.selectGlossaryByWordId(gni[i], wordId);
					if(glossary != null&&glossary.getWordid()==0){
						gnlist.add(gn[i]);
					}
				}
				String tempStr = "";
				for(int j=0;j<gnlist.size();j++){
					tempStr += gnlist.get(j);
					if(j<gnlist.size()-1){
						tempStr += ",";
					}
				}
				out.print("{\"glossary\":\""+tempStr+"\",\"type\":1}");
			}
			else{
				out.print("{\"type\":0}");//还没创建任何表
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
