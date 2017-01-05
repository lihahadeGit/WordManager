package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Random;

import bean.db.DBBean;
import bean.vo.Glossary;

public class GlossaryDao {
	
	//glossaryName即用户给生词表取的名字,glossaryNameInternal表示对应的生词表存进数据库时的名字
	public String createGlossary(String tableName,String oldGlossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String glossaryNameInternal = "";
		do{
			Random r = new Random();
			int random = r.nextInt(100);
			glossaryNameInternal = "gt"+random;
		}while(oldGlossaryNameInternal.contains(glossaryNameInternal));
		String sql = "create table "+glossaryNameInternal+" (wordid int,next int,type int,foreign key(wordid) references "+tableName+" (wordid),primary key(wordid))";
		try {
			db.executeCreate(sql,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return glossaryNameInternal;
	}
	
	public Glossary selectGlossary(String glossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from "+glossaryNameInternal+" where next = null";
		Glossary glossary;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql,null);
			glossary = new Glossary();
			if(rs.next()){
				glossary.setWordid(Integer.parseInt(rs.getString(1)));
				glossary.setNext(Integer.parseInt(rs.getString(2)));
				glossary.setType(Integer.parseInt(rs.getString(3)));
			}
			return glossary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateGlossary(String glossaryNameInternal,int wordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "update "+glossaryNameInternal+" set next = "+wordId;
		int returnValue;
		try {
			returnValue = db.executeUpdateForGlossary(sql,null);
			return returnValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public int insertIntoGlossary(String glossaryNameInternal,int wordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
	    String sql = "insert into "+glossaryNameInternal+" values("+wordId+",null,1)";
	    int returnValue;
		try {
			returnValue = db.executeUpdateForGlossary(sql,null);
			return returnValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
