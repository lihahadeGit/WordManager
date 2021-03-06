package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.db.DBBean;
import bean.vo.User;

public class UserDao {
	//插入用户到数据库，此处有疑问？是把User对象作为参数好，还是把params作为参数好
	//params包含username和password	
	public int insertUser(String username,String password){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "insert into user values(?,?,?,?,?)";
		int returnValue = 0;
		String wordTableName = username+"wt";
		String glossaryName = null;
		String glossaryNameInternal = null;
		ArrayList params = new ArrayList();
		params.add(username);
		params.add(password);
		params.add(wordTableName);
		params.add(glossaryName);
		params.add(glossaryNameInternal);
		WordDao wd = new WordDao();
		try{
			 returnValue = db.executeUpdateForUser(sql, params);
			 System.out.println(returnValue);
			 wd.createWordTable(wordTableName);
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	public int deleteUser(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "delete from user where username = '"+username+"'";
		int returnValue = 0;
		try{
			returnValue = db.executeUpdateForUser(sql, null);
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	//用户可以修改自己的密码，不可以修改用户名和wordtable
	public int updateUser(String username,String password){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "update user set password= '"+password+"'"+" where username= '"
		+username+"'";
		int returnValue = 0;
		try{
			returnValue = db.executeUpdateForUser(sql, null);
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	public int updateGlossaryInfo(String username,String newGlossaryName,String newGlossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "update user set glossaryname = '"+newGlossaryName+"' , glossarynameinternal = '"+newGlossaryNameInternal+"' where username = '"+username+"'";
		int returnValue;
		try {
			returnValue = db.executeUpdateForUser(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	public int selectUser(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from user where username = '"+username+"'";
		int returnValue = 0;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql, null);
			if(rs != null){
				rs.last();
				returnValue = rs.getRow();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return returnValue;
	}
	
	public String selectPassword(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select password from user where username = '"+username+"'";
		String password = null;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql, null);
			if(rs.next()){
				password = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			password = "-1";
			return password;
		}
		
		return password;
	}
	
	public String selectWordTable(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select wordtable from user where username = '"+username+"'";
		String wordtable = null;
		ResultSet rs = null;
		try {
			rs = db.executeQuery(sql, null);
			if(rs.next()){
				wordtable = rs.getString(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			wordtable = "-1";
			return wordtable;
		}
		
		return wordtable;
	}
	
	public ArrayList<String> selectGlossaryInfoFromUser(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select glossaryname,glossarynameinternal from user where username = '"+username+"'";
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			rs = db.executeQuery(sql, null);
			if(rs.next()){
				if(rs.getString(1) != null&&rs.getString(2) != null){
					String glossaryName = rs.getString(1);
					String glossaryNameInternal = rs.getString(2);
					list.add(glossaryName);
					list.add(glossaryNameInternal);
				}
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
