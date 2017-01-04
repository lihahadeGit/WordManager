package bean.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import bean.Conver;

public class DBBean {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Connection getConnection(){
		if(conn == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://127.0.0.1/wordmanager";
				String user = "root";
				String password = "lihaha";
				
				conn = DriverManager.getConnection(url,user,password);
				
			} catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return conn;
	}

	public ResultSet executeQuery(String sql, ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		
		if(params != null){
			Iterator i = params.iterator();
			
			int index = 1;
			while(i.hasNext()){
				String temp = (String)i.next();
				pstmt.setString(index, temp);
				index ++;
			}
			
		}
		
		rs = pstmt.executeQuery();
		
		
		return rs;
	}
	
	public int executeUpdateForUser(String sql,ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		
		if(params != null){
			Iterator i = params.iterator();
			
			int index = 1;
			while(i.hasNext()){
				String temp = (String)i.next();
				pstmt.setString(index, temp);
				index++;
			}
		}
		System.out.println(sql);
		return pstmt.executeUpdate();
	}
	
	public int executeUpdateForWord(String sql,ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		
		if(params != null){
			pstmt.setNull(1,Types.NUMERIC);
			pstmt.setString(2,(String)params.get(1));
			pstmt.setString(3,(String)params.get(2));
			pstmt.setString(4,(String)params.get(3));
			pstmt.setString(5,(String)params.get(4));
			pstmt.setInt(6,Integer.parseInt(String.valueOf(params.get(5))));
			pstmt.setInt(7,Integer.parseInt(String.valueOf(params.get(6))));
			Conver conver = new Conver();
			pstmt.setDate(8,conver.ConverToDate(String.valueOf(params.get(7))));
			pstmt.setString(9,(String)params.get(8));
		}
		
		return pstmt.executeUpdate();
	}
	
	public int executeUpdateForGlossaryInfo(String sql,ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		
		if(params != null){
		}
		
		return pstmt.executeUpdate();
	}
	
	public boolean executeCreate(String sql,ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		
		if(params != null){
			Iterator i = params.iterator();
			
			int index = 1;
			while(i.hasNext()){
				String temp = (String)i.next();
				pstmt.setString(index, temp);
				index++;
			}
		}
		
		return pstmt.execute();
	}
	
	public int getRowsCountOfWordTable(String sql,ArrayList params) throws Exception{
		if(conn == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = conn.prepareStatement(sql);
		if(params != null){
			Iterator i = params.iterator();
			
			int index = 1;
			while(i.hasNext()){
				String temp = (String)i.next();
				pstmt.setString(index, temp);
				index++;
			}
		}
		
		rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		return count;
	}
	
	public void close(){
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
