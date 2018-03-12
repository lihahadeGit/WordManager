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
import bean.vo.GlossaryData;

/**
 * Servlet implementation class GetInitGlossaryDataServlet
 */
public class GetInitGlossaryDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInitGlossaryDataServlet() {
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
			String glossaryName = request.getParameter("glossaryName");
			if(wordTable == null){
				out.print("{\"type\":-1}");//查询wordTable失败
			}
			else{
				ArrayList<String>  list= userdao.selectGlossaryInfoFromUser(username);
				if(list == null){
					out.print("{\"type\":-2}");//查询glossaryinfo信息时出错
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
							ArrayList<GlossaryData> glossaryDataList = glossarydao.selectAllGlossaryData(glossaryNameInternal, wordTable);
							if(glossaryDataList == null){
								out.print(-3);//连接查询时出错
							}
							else{
								boolean first = true;
								String finalStr = "";
								String tempStr;
								for(int i=0;i<glossaryDataList.size();i++){
									tempStr = (first?"":",")+"{"+glossaryDataList.get(i).toJsonString()+"}";
									first = false;
									finalStr += tempStr;
								}
								finalStr = "{\"words\":["+finalStr+"],\"type\":1}";
								out.print(finalStr);
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
