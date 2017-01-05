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
import bean.dao.GlossaryInfoDao;
import bean.dao.UserDao;
import bean.vo.GlossaryInfo;

/**
 * Servlet implementation class CreateGlossaryServlet
 */
public class CreateGlossaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGlossaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print(3);//用户未登录
		}
		else{
			String username = (String)session.getAttribute("username");
			UserDao userdao = new UserDao();
			String wordTable = userdao.selectWordTable(username);
			if(wordTable.equals("-1") || wordTable == null){
				out.print(-2);//查询wordTable的过程中出错
			}else{
				String glossaryName = request.getParameter("glossaryName");
				GlossaryInfoDao glossaryinfodao = new GlossaryInfoDao();
				ArrayList<String> list = glossaryinfodao.selectUserFromGlossaryInfo(username);
				if(list != null){
					if(list.size() == 2){
						String oldGlossaryName = (String)list.get(0);
						String oldGlossaryNameInternal = (String)list.get(1);
						if(oldGlossaryName.contains(glossaryName)){
							out.print(2);//该生词表的名字已存在
						}
						else{
							GlossaryDao glossarydao = new GlossaryDao();
							String glossaryNameInternal = glossarydao.createGlossary(wordTable,oldGlossaryNameInternal);
							if(glossaryNameInternal == null){
								out.print(-3);//创建glossary表出错
							}
							else{
								String newGlossaryName = oldGlossaryName+","+glossaryName;
								String newGlossaryNameInternal = oldGlossaryNameInternal+","+glossaryNameInternal;
								int returnValue = glossaryinfodao.updateGlossaryInfo(username, newGlossaryName, newGlossaryNameInternal);
								out.print(returnValue);//-1表示更新数据库时出错，0表示该用户不存在，1表示更新成功
							}
						}
				     }
					else{
						GlossaryDao glossarydao = new GlossaryDao();
						String glossaryNameInternal = glossarydao.createGlossary(wordTable,"");
						if(glossaryNameInternal == null){
							out.print(-3);//创建glossary表出错
						}
						else{
							int returnValue = glossaryinfodao.insertIntoGlossaryInfo(username, glossaryName, glossaryNameInternal);
							if(returnValue == -1){
								out.print(-4);//插入数据到glossaryInfo失败
							}
							else if(returnValue == 1){
								out.print(3);//插入数据成功
							}
						}
					}
				}
				else{
					out.print(-5);//查询glossaryinfo信息时出错
				}
				
				/*else{
					String glossaryName = request.getParameter("glossaryName");
					GlossaryInfoDao glossaryinfodao = new GlossaryInfoDao();
					//如果该用户存在于glossaryInfo表中，则更新，否则，插入该条用户记录
					if(glossaryinfodao.selectUserFromGlossaryInfo(username) != null){
						ArrayList<String> list = glossaryinfodao.selectUserFromGlossaryInfo(username);
						if(list != null){
							if(list.size() == 2){
								String oldGlossaryName = (String)list.get(0);
								String oldGlossaryNameInternal = (String)list.get(1);
								if(oldGlossaryName.equals(glossaryName)){
									out.print(2);//该生词表的名字已存在
								}
								else{
									String newGlossaryName = oldGlossaryName+","+glossaryName;
									String newGlossaryNameInternal = oldGlossaryNameInternal+","+glossaryNameInternal;
									int returnValue = glossaryinfodao.updateGlossaryInfo(username, newGlossaryName, newGlossaryNameInternal);
									out.print(returnValue);//-1表示更新数据库时出错，0表示该用户不存在，1表示更新成功
								}
							}
							else{
								int returnValue = glossaryinfodao.insertIntoGlossaryInfo(username, glossaryName, glossaryNameInternal);
								if(returnValue == -1){
									out.print(-3);//插入数据到glossaryInfo失败
								}
								else if(returnValue == 1){
									out.print(1);//插入数据成功
								}
							}
						}else{
							out.print(-5);//查询glossaryinfo信息时出错
						}
					}else{
						out.print(-4);//查找GlossaryInfo信息出错
					}
				}*/
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
