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

import bean.dao.GlossaryInfoDao;

/**
 * Servlet implementation class InsertWordToGlossary
 */
public class InsertWordToGlossaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertWordToGlossaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print(3);//用户未登录
		}
		else{
			String glossaryName = request.getParameter("glossaryName");
			String username = (String)session.getAttribute("username");
			GlossaryInfoDao glossaryinfodao = new GlossaryInfoDao();
			ArrayList<String>  list= glossaryinfodao.selectUserFromGlossaryInfo(username);
			if(list == null){
				out.print(-5);//查询glossaryinfo信息时出错
			}
			else{
				if(list.size() == 2){
					String oldGlossaryName = list.get(0);
					String oldGlossaryNameInternal = list.get(1);
					String[] gn = oldGlossaryName.split(",");
					String[] gni = oldGlossaryNameInternal.split(",");
					int index = -1;
					for(int i = 0;i<gn.length;i++){
						if(gn[i].equals(glossaryName)){
							index = i;
							break;
						}
					}
					if(index != -1){
						String glossaryNameInternal = gni[index];
					}
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
