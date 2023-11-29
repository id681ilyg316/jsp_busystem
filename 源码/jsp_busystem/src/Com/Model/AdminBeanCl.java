package Com.Model;

import java.sql.*;

import Com.db.db;

public class AdminBeanCl {
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	

	public boolean check(String UserName,String PassWord)
	{
		boolean b=false;
		ct=new db().getConn();
		try {
			
			sm=ct.createStatement();
			rs=sm.executeQuery("select * from Admin where UserName='"+UserName+"' limit 0,1");
			if(rs.next())
			{
				if(rs.getString(3).trim().equals(PassWord))
				{
					b=true;
				}
				
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return b;
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



