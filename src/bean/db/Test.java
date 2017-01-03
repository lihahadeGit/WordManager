package bean.db;

import java.sql.Connection;

public class Test {
	public int getCount(){
		DBBean db = new DBBean();
		Connection conn = db.getConnection();
		String sql = "select count(*) from user";
		int count = -1;
		 try {
			count = db.getRowsCountOfWordTable(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return count;
	}
	public static void main(String args[]){
		Test t = new Test();
		System.out.println(t.getCount());
	}
	
}
