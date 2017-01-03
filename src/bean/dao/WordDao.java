package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import bean.Conver;
import bean.db.DBBean;
import bean.vo.User;
import bean.vo.Word;

public class WordDao {
	public boolean createWordTable(String wordTableName){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		int createNum;
		String sql = "create table "+wordTableName+"(wordid int auto_increment,textchinese varchar(20),textenglish varchar(20),alphabetUK varchar(20),alphabetUS varchar(20),reciteflag int,recitenum int,addtime date,resourceurl varchar(20),primary key(wordid));";
	    try{
	    	db.executeCreate(sql, null);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	//此处代表出错
	    	return false;
	    }
	    
	    return true;
	}
	
	//插入单词到单词表，wordTableName:用户的wordTable属性值；params:单词属性值的集合，除了wordId
	public int insertWord(String wordTableName,String test_Chinese,
			String test_English,String alphabetUK,String alphabetUS,int reciteFlag,
			int reciteNum,Date addTime,String resourceUrl){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "insert into "+wordTableName+" values(?,?,?,?,?,?,?,?,?)";
		int returnValue = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList params = new ArrayList();
		/*int rowsCount = this.getRowsCount(wordTableName);
		ArrayList newlist = new ArrayList();
		newlist.set(0,String.valueOf(rowsCount));
		if(params != null){
			Iterator<String> i = params.iterator();
			int index = 1;
			while(i.hasNext()){
				newlist.set(index,i);
				index++;
			}
		}*/
		try{
			 params.add(0,null);
			 params.add(1, test_Chinese);
			 params.add(2, test_English);
			 params.add(3, alphabetUK);
			 params.add(4, alphabetUS);
			 params.add(5, reciteFlag);
			 params.add(6, reciteNum);
			 params.add(7, addTime);
			 params.add(8, resourceUrl);
			 returnValue = db.executeUpdateForWord(sql, params);
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	/*public int deleteWord(User user,Word word){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String wordTable = user.getWordTable();
		String wordId = word.getWordId();
		String sql = "delete * from "+wordTable+" where wordid= "+wordId;
		int returnValue = 0;
		try {
			returnValue = db.executeUpdate(sql,null);
		} catch (Exception e) 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}*/
	
	public ArrayList<Word> getWord(String wordTable){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from "+wordTable+" order by addtime desc limit 10";
	    ArrayList<Word> list = new ArrayList<Word>();
	    ResultSet rs = null;
	    try {
			rs = db.executeQuery(sql, null);
			while(rs.next()){
				Word word = new Word();
				word.setWordProperty(rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			    list.add(word);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return list;
	}
	
	public ArrayList<Word> getWordsByStep(int step,String wordTable,String dateStr) throws Exception{
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		Conver conver = new Conver();
		Date date = conver.ConverToDate(dateStr);
		String sql = "select * from "+wordTable+"where addtime< "+date+" order by addTime desc limit 3";
		ArrayList<Word> list = new ArrayList<Word>();
	    ResultSet rs = null;
	    try {
			rs = db.executeQuery(sql, null);
			while(rs.next()){
				Word word = new Word();
				word.setWordProperty(rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			    list.add(word);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return list;
	}
	public int getRowsCount(String wordTableName){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String getCountSql = "select count(*) from "+wordTableName;
		int count = 0;
		try {
			count = db.getRowsCountOfWordTable(getCountSql, null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return count;
	}

}
