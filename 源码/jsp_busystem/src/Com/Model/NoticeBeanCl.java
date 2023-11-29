package Com.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Com.db.db;

public class NoticeBeanCl {
	
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	
	int pageNow=0;
	int pageCount=0;
	int rowCount=0;
	int pageSize=10;
	

    public boolean AddInfo(String Title,String Content,String Time){
    	boolean a=false;
    	try {
    		ct=new db().getConn();
			sm=ct.createStatement();
			
			int i=sm.executeUpdate("insert into Notice(Title,Content,Time) values('"+Title+"','"+Content+"','"+Time+"')");
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
    

    public NoticeBean getNotice(){
    	NoticeBean nb = new NoticeBean();
    	try {
    		ct=new db().getConn();
			sm=ct.createStatement();
			
			rs=sm.executeQuery("select * from Notice order by Time desc limit 0,1");
			
			while(rs.next()){
				
				nb.setID(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setContent(rs.getString(3));
				nb.setTime(rs.getString(4));
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		return nb;
    }
    

    public synchronized boolean delNoticeInfo(String NoticeId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		sm=ct.createStatement();
            int temp = sm.executeUpdate("delete  from Notice where ID='"+NoticeId+"'") ;
    		

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
    		 

    public synchronized boolean delAllNoticeInfo(String[] NoticeId)
    {
    	boolean b=false;
    	try {
			
    		ct=new db().getConn();
    		ct.setAutoCommit(false) ;
    		sm=ct.createStatement();
    		for(int i=0;i<NoticeId.length;i++){
    	       	
    	       	sm.addBatch("delete  from Notice where ID='"+NoticeId[i]+"'");
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

	public boolean ReviseInfo(String NoticeId,String Title,String Content,String Time){
		boolean a=false;
		try {
			ct=new db().getConn();
			sm=ct.createStatement();
			
			int i=sm.executeUpdate("update Notice set Title='"+Title+"', Content='"+Content+"', Time='"+Time+"' where ID='"+NoticeId+"'");
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
    		rs=sm.executeQuery("select count(*) from Notice");
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

	public ArrayList getNoticeinfoByPage(int pageNow){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select top "+pageSize+" * from Notice where ID not in (select top "+pageSize*(pageNow-1)+" ID from Notice)");
			
			while(rs.next()){
				NoticeBean nb = new NoticeBean();
				nb.setID(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setContent(rs.getString(3));
				nb.setTime(rs.getString(4));
				
				al.add(nb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getNoticeinfobysql(String sql){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			
			while(rs.next()){
				NoticeBean nb = new NoticeBean();
				nb.setID(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setContent(rs.getString(3));
				nb.setTime(rs.getString(4));
				
				al.add(nb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList getNoticeinfoByNoticeID(String NoticeId){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select top 1 * from Notice where ID = '"+NoticeId+"'");
			
			if(rs.next()){
				NoticeBean nb = new NoticeBean();
				nb.setID(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setContent(rs.getString(3));
				nb.setTime(rs.getString(4));
				
				al.add(nb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	
	

	public ArrayList getNoticeinfo(){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Notice ");
			
			while(rs.next()){
				NoticeBean nb = new NoticeBean();
				nb.setID(rs.getString(1));
				nb.setTitle(rs.getString(2));
				nb.setContent(rs.getString(3));
				nb.setTime(rs.getString(4));
				
				al.add(nb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	
	public String getSystemTime(){
		
			String temp = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			temp = sdf.format(new java.util.Date());
			
			return temp;
			
		
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
