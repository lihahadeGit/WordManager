package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import bean.db.DBBean;
import bean.vo.Glossary;
import bean.vo.GlossaryData;

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
	
	public Glossary selectTheLastGlossary(String glossaryNameInternal,int type){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from "+glossaryNameInternal+" where next is null and type = "+type;
		Glossary glossary;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql,null);
			glossary = new Glossary();
			if(rs.next()){
				glossary.setWordid(Integer.parseInt(rs.getString(1)));
				glossary.setNext(0);
				glossary.setType(Integer.parseInt(rs.getString(3)));
			}
			return glossary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Glossary selectGlossaryByWordId(String glossaryNameInternal,int wordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from "+glossaryNameInternal+" where wordid = "+wordId;
		Glossary glossary;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql,null);
			glossary = new Glossary();
			if(rs.next()){
				glossary.setWordid(Integer.parseInt(rs.getString(1)));
				if(rs.getString(2) == null){
					glossary.setNext(0);
				}
				else{
					glossary.setNext(Integer.parseInt(rs.getString(2)));
				}
				glossary.setType(Integer.parseInt(rs.getString(3)));
			}
			return glossary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Glossary selectGlossaryByNext(String glossaryNameInternal,int next){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from "+glossaryNameInternal+" where next = "+next;
		Glossary glossary;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql,null);
			glossary = new Glossary();
			if(rs.next()){
				glossary.setWordid(Integer.parseInt(rs.getString(1)));
				if(rs.getString(2) == null){
					glossary.setNext(0);
				}
				else{
					glossary.setNext(Integer.parseInt(rs.getString(2)));
				}
				glossary.setType(Integer.parseInt(rs.getString(3)));
			}
			return glossary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> selectAllWordId(String glossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select wordid from "+glossaryNameInternal;
		ResultSet rs = null;
		ArrayList<String> wordIdList = new ArrayList<String>();
		try {
			rs = db.executeQuery(sql, null);
			while(rs.next()){
				wordIdList.add(rs.getString(1));
			}
			return wordIdList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int selectGlossaryType(String glossaryNameInternal,int wordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select type from "+glossaryNameInternal+" where wordid = "+wordId;
		int type = 0;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql, null);
			if(rs.next()){
				type = Integer.parseInt(rs.getString(1));
			}
			return type;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<GlossaryData> selectAllGlossaryData(String glossaryNameInternal,String wordTable){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select "+wordTable+".wordid,next,type,textchinese,textenglish,alphabetUK,alphabetUS,addTime from "+wordTable+","+glossaryNameInternal+" where "+glossaryNameInternal+".wordid = "+wordTable+".wordid";
		System.out.println(sql);
		ResultSet rs = null;
		ArrayList<GlossaryData> glossaryList = new ArrayList<GlossaryData>();
		try {
			rs = db.executeQuery(sql, null);
			while(rs.next()){
				GlossaryData glossarydata = new GlossaryData();
				glossarydata.setWordId(rs.getInt(1));
				glossarydata.setNext(rs.getInt(2));
				glossarydata.setType(rs.getInt(3));
				glossarydata.setText_Chinese(rs.getString(4));
				glossarydata.setText_English(rs.getString(5));
				glossarydata.setAlphabetUK(rs.getString(6));
				glossarydata.setAlphabetUS(rs.getString(7));
				glossarydata.setAddTime(rs.getDate(8));
				glossaryList.add(glossarydata);
			}
			return glossaryList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateGlossary(String glossaryNameInternal,int wordId,int nextWordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql;
		if(nextWordId == 0){
			sql = "update "+glossaryNameInternal+" set next = "+null+" where wordid = "+wordId;
		}
		else{
			sql = "update "+glossaryNameInternal+" set next = "+nextWordId+" where wordid = "+wordId;
		}
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
	
	public int updateGlossaryType(String glossaryNameInternal,int wordId,int newType){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "update "+glossaryNameInternal+" set type = "+newType+" where wordid = "+wordId;
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
	
	public int deleteGlossary(String glossaryNameInternal,int wordId){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
	    String sql = "delete from "+glossaryNameInternal+" where wordId = "+wordId;
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
