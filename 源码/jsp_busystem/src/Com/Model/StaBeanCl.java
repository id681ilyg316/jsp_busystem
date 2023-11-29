package Com.Model;

import java.sql.*;
import java.util.ArrayList;

import Com.db.db;


public class StaBeanCl {
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	
	int pageNow=0;
	int pageCount=0;
	int rowCount=0;
	int pageSize=10;
	

    public synchronized boolean delStaInfo(String StaId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		sm=ct.createStatement();
            int temp = sm.executeUpdate("delete  from Station where ID='"+StaId+"'") ;
    		

    		    if(temp==1)
    		    {

    		      b=true;	
    	       
    		    } 
    		    
    			} catch (Exception e) {

    				e.printStackTrace();
    			}finally
    			{
    				this.colse();
    			}
    			 
    			 return b;
    		 }
    		 

    public synchronized boolean delAllStaInfo(String[] StaId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		ct.setAutoCommit(false) ;
    		sm=ct.createStatement();
    		for(int i=0;i<StaId.length;i++){
    	       	
    	       	sm.addBatch("delete  from Station where ID='"+StaId[i]+"'");
    	       }
    	       int temp[] = sm.executeBatch() ;
    		

    		    if(temp.length>0)
    		    {

    		      b=true;	
    	         ct.commit() ;
    		    } 
    		    
    			} catch (Exception e) {

    				try {
    					ct.rollback() ;
    				} catch (SQLException e1) {

    					e.printStackTrace();
    				}
    				e.printStackTrace();
    			}finally
    			{
    				this.colse();
    			}
    			 
    			 return b;
    		 }
    

    public boolean AddInfo(String StaId,String StaName){
    	boolean a=false;
    	try {
    		ct=new db().getConn();
			sm=ct.createStatement();
			
			int i=sm.executeUpdate("insert into Station(ID,Name) values('"+StaId+"','"+StaName+"')");
			if(i==1){
				a=true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		return a;
    }
    		 
    	

	public boolean ReviseInfo(String StaId,String StaName){
		boolean a=false;
		try {
			ct=new db().getConn();
			sm=ct.createStatement();
			int i=sm.executeUpdate("update Station set Name='"+StaName+"' where ID='"+StaId+"'");
			if(i==1){
				a=true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		return a;
	}
	
	
	

	public int getPageCountBySQL(String sql){
    	try {
    		ct=new db().getConn();
    		sm=ct.createStatement();
    		 rs=sm.executeQuery(sql);
    		 if(rs.next())
    		 {
    		    rowCount=rs.getInt(1);
    		 }
    		 if(rowCount%pageSize==0)
    		 {
    		    pageCount=rowCount/pageSize;
    		 }else
    		 {
    		    pageCount=rowCount/pageSize+1;
    		    
    		 }
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
    	return  pageCount;
    }
	

    public int getPageCount(){
    	try {
    		ct=new db().getConn();
    		sm=ct.createStatement();
    		 rs=sm.executeQuery("select count(*) from Station");
    		 if(rs.next())
    		 {
    		    rowCount=rs.getInt(1);
    		 }
    		 if(rowCount%pageSize==0)
    		 {
    		    pageCount=rowCount/pageSize;
    		 }else
    		 {
    		    pageCount=rowCount/pageSize+1;
    		    
    		 }
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
    	return  pageCount;
    }
    

	public ArrayList getStainfoByPage(int pageNow){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Station limit " + pageSize * (pageNow - 1) + "," + pageSize);
			
			while(rs.next()){
				StaBean sb = new StaBean();
				sb.setID(rs.getString(1));
				sb.setStaName(rs.getString(2));

				
				al.add(sb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getStainfobysql(String sql){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			
			while(rs.next()){
				StaBean sb = new StaBean();
				sb.setID(rs.getString(1));
				sb.setStaName(rs.getString(2));
				
				al.add(sb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getStainfoByStaID(String StaId){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Station where ID = '"+StaId+"' limit 0,1");
			
			if(rs.next()){
				StaBean sb = new StaBean();
				sb.setID(rs.getString(1));
				sb.setStaName(rs.getString(2));

				al.add(sb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	
	

	public ArrayList getStainfo(){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Station ");
			
			while(rs.next()){
				StaBean sb = new StaBean();
				sb.setID(rs.getString(1));
				sb.setStaName(rs.getString(2));
				
				al.add(sb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	
	public void colse(){
		
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(sm!=null){
				sm.close();
				sm=null;
			}
			if(ct!=null){
				ct.close();
				ct=null;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
}
