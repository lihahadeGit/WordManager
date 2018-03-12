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
 * Servlet implementation class RememberServlet
 */
@WebServlet("/RememberServlet")
public class RememberAndForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RememberAndForgetServlet() {
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
			out.print(3);//用户未登录
		}
		String glossaryName = request.getParameter("glossaryName");
		int wordId = Integer.parseInt(request.getParameter("wordId"));
		int newType = Integer.parseInt(request.getParameter("newType"));
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
					int type = glossarydao.selectGlossaryType(glossaryNameInternal, wordId);
					if(type == -1){
						out.print(-2);//查询type时出错
					}
					else if(type == 0){
						out.print(2);//未找到对应的type
					}
					else{
						Glossary glossary = glossarydao.selectGlossaryByWordId(glossaryNameInternal, wordId);
						Glossary priorglossary;
						Glossary nextglossary;
						//如果type不变的话，将该单词插到队尾
						if(type == newType){
							if(glossary == null){
								out.print(-3);//根据id查询glossary对象时出错
							}
							else if(glossary.getWordid() == 0){
								out.print(3);//未查询到对应的glossary对象
							}
							else{
								//B->null及B的情况
								if(glossary.getNext() == 0){
									priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
									if(priorglossary == null){
										out.print(-4);//根据next查询glossary对象出错
									}
									else if(priorglossary.getWordid() == 0){
										//什么都不做，保持原来的状态不变
									}
									else{
										/*int returnValue = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),0);
										if(returnValue == 1){
											Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal, type);
											if(thelastglossary == null){
												out.print(-6);//查询出错
											}
											else if(thelastglossary.getWordid() == 0){
												out.print(5);//未查询到相应的对象
											}
											else{
												int updateResult = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
											    if(updateResult == -1){
											    	out.print(-7);//更新队尾glossary对象失败
											    }
											}
										}
										else{
											out.print(-5);//更新glossary失败
										}*/
									}
								}
								//A->B->C及B->其他
								else{
									priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
									if(priorglossary != null){
										if(priorglossary.getWordid() != 0){
											int result = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),glossary.getNext());
											if(result == 1){
												Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal, type);
											    if(thelastglossary != null&&thelastglossary.getWordid() != 0){
											    	glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
											    	if(result == 1){
											    		glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
											    	}
											    }
											}
										}
										else{
											Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal, type);
										    if(thelastglossary != null&&thelastglossary.getWordid() != 0){
										    	int result = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
										    	if(result == 1){
										    		glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
										    	}
										    }
										}
									}
								}
								
							}
							//Glossary glossary = glossarydao.selectTheLastGlossary(glossaryNameInternal, type);
							
						}
						else{
							if(glossary != null&&glossary.getWordid() != 0){
								//B->null及B的情况
								if(glossary.getNext() == 0){
									priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
									if(priorglossary != null){
										if(priorglossary.getWordid() != 0){
											int result1 = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),0);
											if(result1 == 1){
												Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal,newType);
												if(thelastglossary != null){
													if(thelastglossary.getWordid() != 0){
														int result2 = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
													    if(result2 == 1){
													    	int result3 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
													    	if(result3 == 1){
														    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
													    	}
													    }
													}
													else{
														int result4 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
												    	if(result4 == 1){
													    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
												    	}
													}
												}
											}
										}
										else{
											Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal,newType);
											if(thelastglossary != null){
												if(thelastglossary.getWordid() != 0){
													int result5 = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
													if(result5 == 1){
												    	int result6 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
												    	if(result6 == 1){
													    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
												    	}
												    }
												}
												else{
													int result7 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
											    	if(result7 == 1){
												    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
											    	}
												}
											}
										}
									}
								}
								//A->B->C及B->其他
								else{
									priorglossary = glossarydao.selectGlossaryByNext(glossaryNameInternal, wordId);
									if(priorglossary != null){
										if(priorglossary.getWordid() != 0){
											int result1 = glossarydao.updateGlossary(glossaryNameInternal,priorglossary.getWordid(),glossary.getNext());
											if(result1 == 1){
												Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal,newType);
												if(thelastglossary != null){
													if(thelastglossary.getWordid() != 0){
														int result2 = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
													    if(result2 == 1){
													    	int result3 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
													    	if(result3 == 1){
														    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
													    	}
													    }
													}
													else{
														int result4 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
												    	if(result4 == 1){
													    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
												    	}
													}
												}
											}
										}
										else{
											Glossary thelastglossary = glossarydao.selectTheLastGlossary(glossaryNameInternal,newType);
											if(thelastglossary != null){
												if(thelastglossary.getWordid() != 0){
													int result5 = glossarydao.updateGlossary(glossaryNameInternal,thelastglossary.getWordid(),wordId);
												    if(result5 == 1){
												    	int result6 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
												    	if(result6 == 1){
													    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
												    	}
												    }
												}
												else{
													int result7 = glossarydao.updateGlossary(glossaryNameInternal, wordId, 0);
											    	if(result7 == 1){
												    	glossarydao.updateGlossaryType(glossaryNameInternal, wordId, newType);
											    	}
												}
											}
										}
									
									}
								}
								
							}
						}
					}
				}
			}
		}
		//int type = glossarydao.selectGlossaryType(glossaryNameInternal, wordId)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
