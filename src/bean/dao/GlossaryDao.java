package bean.dao;

import java.sql.Connection;
import java.util.Random;

import bean.db.DBBean;

public class GlossaryDao {
	
	//glossaryName即用户给生词表取的名字,glossaryNameInternal表示对应的生词表存进数据库时的名字
	public String createGlossary(String tableName){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		Random r = new Random();
		int random = r.nextInt(100);
		String glossaryNameInternal = "gt"+random;
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

}
