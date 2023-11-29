package Com.Model;

import java.sql.*;
import java.util.ArrayList;

import Com.db.db;


public class LineBeanCl {
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	
	int pageNow=0;
	int pageCount=0;
	int rowCount=0;
	int pageSize=10;
	

    public synchronized boolean delLineInfo(String LineId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		sm=ct.createStatement();
            int temp = sm.executeUpdate("delete  from Line where LineID='"+LineId+"'") ;
    		

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
    		 

    public synchronized boolean delAllLineInfo(String[] LineId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		ct.setAutoCommit(false) ;
    		sm=ct.createStatement();
    		for(int i=0;i<LineId.length;i++){
    	       	
    	       	sm.addBatch("delete  from Line where LineID='"+LineId[i]+"'");
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

    public boolean AddInfo(String Bus_Id,String Sta_Id,String Distance){
    	boolean a=false;
    	try {
    		ct=new db().getConn();
			sm=ct.createStatement();
			
			int i=sm.executeUpdate("insert into Line(Bus_ID,Sta_ID,Distance) values('"+Bus_Id+"','"+Sta_Id+"','"+Distance+"')");
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
    		 
    	

	public boolean ReviseInfo(String LineId,String Bus_Id,String Sta_Id,String Distance){
		boolean a=false;
		try {
			ct=new db().getConn();
			sm=ct.createStatement();
			int i=sm.executeUpdate("update Line set Bus_ID='"+Bus_Id+"',Sta_ID='"+Sta_Id+"', Distance='"+Distance+"' where LineID='"+LineId+"'");
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
    		 rs=sm.executeQuery("select count(*) from Line");
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

	public ArrayList getLineinfoByPage(int pageNow){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Line limit " + pageSize * (pageNow - 1) + "," + pageSize);
			
			while(rs.next()){
				LineBean lb = new LineBean();
				lb.setLineID(rs.getString(1));
				lb.setBus_ID(rs.getString(2));
				lb.setSta_ID(rs.getString(3));
				lb.setDistance(rs.getString(4));
				
				al.add(lb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getLineinfobysql(String sql){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			
			while(rs.next()){
				LineBean lb = new LineBean();
				lb.setLineID(rs.getString(1));
				lb.setBus_ID(rs.getString(2));
				lb.setSta_ID(rs.getString(3));
				lb.setDistance(rs.getString(4));
				
				al.add(lb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getLineinfoByLineID(String LineId){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select top 1 * from Line where LineID = '"+LineId+"'");
			
			if(rs.next()){
				LineBean lb = new LineBean();
				lb.setLineID(rs.getString(1));
				lb.setBus_ID(rs.getString(2));
				lb.setSta_ID(rs.getString(3));
				lb.setDistance(rs.getString(4));
				
				al.add(lb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	
	

	public ArrayList getLineinfo(){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Line ");
			
			while(rs.next()){
				LineBean lb = new LineBean();
				lb.setLineID(rs.getString(1));
				lb.setBus_ID(rs.getString(2));
				lb.setSta_ID(rs.getString(3));
				lb.setDistance(rs.getString(4));
				
				al.add(lb);
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
