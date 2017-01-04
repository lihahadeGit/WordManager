package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.db.DBBean;
import bean.vo.GlossaryInfo;

public class GlossaryInfoDao {

	public int updateGlossaryInfo(String username,String newGlossaryName,String newGlossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "update glossaryinfo set glossaryname = '"+newGlossaryName+"' , glossarynameinternal = '"+newGlossaryNameInternal+"' where username = '"+username+"'";
		int returnValue;
		try {
			returnValue = db.executeUpdateForGlossaryInfo(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	public int insertIntoGlossaryInfo(String username,String glossaryName,String glossaryNameInternal){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "insert into glossaryinfo values('"+username+"','"+glossaryName+"','"+glossaryNameInternal+"')";
		int returnValue;
		try {
			returnValue = db.executeUpdateForGlossaryInfo(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return returnValue;
	}
	
	public ArrayList<String> selectUserFromGlossaryInfo(String username){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select * from glossaryinfo where username = '"+username+"'";
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			rs = db.executeQuery(sql, null);
			if(rs.next()){
				String glossaryName = rs.getString(2);
				String glossaryNameInternal = rs.getString(3);
				list.add(glossaryName);
				list.add(glossaryNameInternal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return list;
	}

}
