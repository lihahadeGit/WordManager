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
			UserDao userdao = new UserDao();
			ArrayList<String>  list= userdao.selectGlossaryInfoFromUser(username);
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
						GlossaryDao glossarydao = new GlossaryDao();
						Glossary glossary = glossarydao.selectTheLastGlossary(glossaryNameInternal,1);
						if(glossary == null){
							out.print(-2);//查询出错
						}
						else{
							int wordId = glossary.getWordid();
							int nextWordId = Integer.parseInt(request.getParameter("wordId"));
							ArrayList<String> wordIdList = glossarydao.selectAllWordId(glossaryNameInternal);
							if(wordIdList == null){
								out.print(-4);//查询wordid失败
							}
							else{
								boolean exist = false;
								for(int i=0;i<wordIdList.size();i++){
									if(wordIdList.get(i).equals(String.valueOf(nextWordId))){
										exist = true;
										break;
									}
								}
								if(exist){
									out.print(2);//该单词已存在于该glossary表中
								}
								else{
									if(glossary.getWordid() != 0){
										int returnValue = glossarydao.updateGlossary(glossaryNameInternal,wordId,nextWordId);
										if(returnValue == 1){
											int insertResult = glossarydao.insertIntoGlossary(glossaryNameInternal,nextWordId);
											out.print(insertResult);//1表示插入成功，-1表示插入失败 
										}
										else{
											out.print(-3);//更新glossary表失败
										}
									}else{
										int insertResult = glossarydao.insertIntoGlossary(glossaryNameInternal,nextWordId);
										out.print(insertResult);//1表示插入成功，-1表示插入失败 
									}
								}
							}
							System.out.println(glossary.getWordid());
				
						}
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
