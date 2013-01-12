package org.storage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.storage.models.Storage;


public class StorageDao {
	private final static String DRIVER_CLASS_NAME="org.apache.derby.jdbc.ClientDriver";
	private final static String URL="jdbc:derby://localhost:1527/storage;create=true";
	private final static String USERNAMW="";
	private final static String PASSWORD="";
	
	/**
	 * 商品类型常量-
	 * 电器
	 */
	
	public final static String TYPE_WIRING="电器";
	/**
	 * 商品类型常量-
	 * 食品
	 */
	public final static String TYPE_FOOD="食品";
	/**
	 * 商品类型常量-
	 * 服装
	 */
	public final static String TYPE_CLOTHING="服装";
	/**
	 * 商品类型常量-
	 * 日用品
	 */
	public final static String TYPE_COMMODITY="日用品";
	
	
	Connection conn=null;
	Logger log=Logger.getLogger(getClass().toString());
	
	//连接数据库
	public Connection getConnection(){
		try {
			Class.forName(DRIVER_CLASS_NAME);
			
			conn=DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			log.info("没有找到驱动类");
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	//关闭数据库
	public void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				conn=null;
			}
		}
	}
	
	//按字段排序查询
	public List<Storage> listOrderByItem(String item){
		List<Storage> list=new ArrayList<Storage>();
		Connection conn=this.getConnection();
		String sql="select * from tbl_storage order by %s desc";
		sql=String.format(sql, item);
		log.info(sql);
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Storage s=new Storage();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setPrice(rs.getFloat("price"));
				s.setType(rs.getString("type"));
				s.setNumber(rs.getInt("number"));
				s.setMakeTime(rs.getTimestamp("makeTime"));
				s.setUseDay(rs.getInt("useDay"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeConnection(conn);
		}
			
		return list;
	}
	/**
	 * 根據類型查詢
	 * @param typeStr
	 * @return
	 */
	public List<Storage> listByType(String typeStr){
		List<Storage> list=new ArrayList<Storage>();
		Connection conn=this.getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement("select * from tbl_storage where type=?");
			ps.setString(1, typeStr);
			
			ps.executeQuery();
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Storage s=new Storage();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setPrice(rs.getFloat("price"));
				s.setType(rs.getString("type"));
				s.setNumber(rs.getInt("number"));
				s.setMakeTime(rs.getTimestamp("makeTime"));
				s.setUseDay(rs.getInt("useDay"));
				list.add(s);
			}
		
		} catch (SQLException e) {
			log.info("查询语句执行失败");
			e.printStackTrace();
		}finally{
			this.closeConnection(conn);
		}
		
		
		return list;
		
	}
	
	public static void main(String[] args) {
		int i=new StorageDao().listByType("服装").size();
		System.out.println(i);
	}
	
	
}
