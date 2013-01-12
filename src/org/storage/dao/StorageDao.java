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
	 * ��Ʒ���ͳ���-
	 * ����
	 */
	
	public final static String TYPE_WIRING="����";
	/**
	 * ��Ʒ���ͳ���-
	 * ʳƷ
	 */
	public final static String TYPE_FOOD="ʳƷ";
	/**
	 * ��Ʒ���ͳ���-
	 * ��װ
	 */
	public final static String TYPE_CLOTHING="��װ";
	/**
	 * ��Ʒ���ͳ���-
	 * ����Ʒ
	 */
	public final static String TYPE_COMMODITY="����Ʒ";
	
	
	Connection conn=null;
	Logger log=Logger.getLogger(getClass().toString());
	
	//�������ݿ�
	public Connection getConnection(){
		try {
			Class.forName(DRIVER_CLASS_NAME);
			
			conn=DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			log.info("û���ҵ�������");
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	//�ر����ݿ�
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
	
	//���ֶ������ѯ
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
	 * ������Ͳ�ԃ
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
			log.info("��ѯ���ִ��ʧ��");
			e.printStackTrace();
		}finally{
			this.closeConnection(conn);
		}
		
		
		return list;
		
	}
	
	public static void main(String[] args) {
		int i=new StorageDao().listByType("��װ").size();
		System.out.println(i);
	}
	
	
}
