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
 * Servlet implementation class DeleteGlossaryServlet
 */
//用于背单词的时候删除
public class DeleteGlossaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGlossaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("utf-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null||session.getAttribute("username") == null){
			out.print(3);//用户未登录
		}
		String glossaryName = request.getParameter("glossaryName");
		int wordId = Integer.parseInt(request.getParameter("wordId"));
		String username = (String)session.getAttribute("username");
		UserDao userdao = new UserDao();
		ArrayList<String>  list= userdao.selectGlossaryInfoFromUser(username);
		if(list == null){
			out.print(-1);//查询glossaryinfo信息时出错
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
					Glossary glossary = glossarydao.selectGlossaryByWordId(glossaryNameInternal, wordId);
					Glossary priorglossary;
					Glossary nextglossary;
					if(glossary != null&&glossary.getWordid() != 0){
						if(glossary.getNext() == 0){
							priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
							if(priorglossary != null){
								if(priorglossary.getWordid() != 0){
									int result1 = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),0);
									if(result1 == 1){
										int return2 = glossarydao.deleteGlossary(glossaryNameInternal, wordId);
										out.print(return2);//-1表示删除失败，1表示删除成功，0表示未找到对应的wordId
									}
								}
								else{
									int return2 = glossarydao.deleteGlossary(glossaryNameInternal, wordId);
									out.print(return2);//-1表示删除失败，1表示删除成功，0表示未找到对应的wordId
								}
							}
						}
						else{
							priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
							if(priorglossary != null){
								if(priorglossary.getWordid() != 0){
									int result1 = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),wordId);
									if(result1 == 1){
										int return2 = glossarydao.deleteGlossary(glossaryNameInternal, wordId);
										out.print(return2);//-1表示删除失败，1表示删除成功，0表示未找到对应的wordId
									}
								}
								else{
									int return2 = glossarydao.deleteGlossary(glossaryNameInternal, wordId);
									out.print(return2);//-1表示删除失败，1表示删除成功，0表示未找到对应的wordId
								}
							}
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
