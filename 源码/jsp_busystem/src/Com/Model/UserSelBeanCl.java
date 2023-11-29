package Com.Model;

import java.sql.*;
import java.util.ArrayList;

import Com.db.db;


public class UserSelBeanCl {
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;
	
	private static String SelectSta = " (SELECT Bus.Name, Bus.ID, Station.ID AS StaID,  Station.Name AS StaName, Line.Distance "
			+ "FROM Bus "
			+ "INNER JOIN  Line ON Bus.ID = Line.Bus_ID "
			+ "INNER JOIN Station ON Line.Sta_ID = Station.ID) SelectSta ";


	
	

	public ArrayList getinfoByBusName(String BusName){
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select StaName from "+SelectSta+" where Name = '"+BusName+"'order by Distance asc");
			
			while(rs.next()){
				UserSelBean usb = new UserSelBean();
				usb.setStaName(rs.getString(1));

				al.add(usb);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public ArrayList  getinfoByStaName(String StaName){
		ArrayList  al = new ArrayList ();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select Name from "+SelectSta+" where StaName = '"+StaName+"' order by ID asc");
			
			while(rs.next()){
				UserSelBean usb = new UserSelBean();
				usb.setBusName(rs.getString(1));

				al.add(usb.getBusName());
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		
		return al;
	}
	

	public BusBean getBusinfoByBusName(String BusName){
		BusBean bb = new BusBean();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Bus where Name = '"+BusName+"' limit 0,1");
			
			if(rs.next()){
				
				bb.setID(rs.getString(1));
				bb.setBusName(rs.getString(2));
				bb.setRegion(rs.getString(3));
				bb.setBeginTime(rs.getString(4));
				bb.setLastTime(rs.getString(5));
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			this.colse();
		}
		return bb;
	}
	

	public  ArrayList<String> getLineDirect2(String StartSta, String EndSta) {

		ArrayList<String> startBus = getinfoByStaName(StartSta);

		ArrayList<String> MidSta = new ArrayList<String>();

		ArrayList<String> endBus = getinfoByStaName(EndSta);
		ArrayList BestComLine = new ArrayList<String>();
		ArrayList a = new ArrayList();
		int b;
		ArrayList MidSta2 = new ArrayList<String>();

		ArrayList al = new ArrayList<String>();
		for(int k = 0;k<startBus.size();k++){

			ct = new db().getConn();
			try {
				sm = ct.createStatement();
				
				rs = sm.executeQuery("select StaName from "+SelectSta+"  where Name='"+startBus.get(k)+"' and  StaName in(select distinct  StaName from "+SelectSta+" where Name!='"+startBus.get(k)+"') order by Distance");
				
				while(rs.next()){
					
					MidSta.add(rs.getString(1));
				}
				
				}catch (Exception e) {

				e.printStackTrace();
			}finally{
				this.colse();
			}
			ArrayList BetComLine = new ArrayList();
			ArrayList MidSta1 = new ArrayList();
			for(int p = 0;p<MidSta.size();p++){
				ArrayList<String> MidBus = new ArrayList<String>();
				MidBus =getinfoByStaName(MidSta.get(p));
				ArrayList<String> Distance1 = new ArrayList<String>();


				ArrayList<String> ComLine = new ArrayList<String>();

				for(int i = 0; i < MidBus.size(); i++) {
					for(int j = 0; j < endBus.size(); j++) {
						if( MidBus.get(i).toString().equals( endBus.get(j).toString() ) ) {
							ComLine.add(MidBus.get(i));
						}
					}
				}
				if(ComLine.size()>0){
					MidSta1.add(MidSta.get(p));
					Distance1 = getDistance(ComLine,EndSta);
					BetComLine.add(getBestLineByDistance(Distance1,ComLine).get(0));
				}
			}
			if(BetComLine.size()>0){
				ArrayList<String> Distance2 = new ArrayList<String>();
				Distance2 = getDistance(BetComLine,EndSta);
				BestComLine.add(getBestLineByDistance(Distance2,BetComLine).get(0));
				a.add(getBestLineByDistance(Distance2,BetComLine).get(1)); 
				b = Integer.parseInt((String)a.get(0));
				MidSta2.add(MidSta1.get(b));
			}
		}
		al.add(BestComLine);
		al.add(MidSta2);
		return al;
		
	}
	

	public  ArrayList<String> getLineDirect(String StartSta, String EndSta) {
		
		

		ArrayList<String> startBus = getinfoByStaName(StartSta);

		ArrayList<String> endBus = getinfoByStaName(EndSta);


		ArrayList<String> comLine = new ArrayList<String>();
		if(startBus.size()==0){
			comLine.add("start");
		}else if(endBus.size()==0){
			comLine.add("end");
		}else{

			for(int i = 0; i < startBus.size(); i++) {
				for(int j = 0; j < endBus.size(); j++) {
					if( startBus.get(i).toString().equals( endBus.get(j).toString() ) ) {
						comLine.add(startBus.get(i));
					}
				}
			}
		}
		
		return comLine;
	}
	  
	

	public  ArrayList<String> getDistance(ArrayList ComLine,String EndSta){
		
		ct = new db().getConn();
		ArrayList Distance = new ArrayList();
		
			try {
				sm = ct.createStatement();
				for(int i = 0;i<ComLine.size();i++){
					rs = sm.executeQuery("select Distance from "+SelectSta+" where ID='"+ComLine.get(i)+"' and  StaName='"+EndSta+"'");
					
					while(rs.next()){
						UserSelBean usb = new UserSelBean();
						usb.setDistance(rs.getString(1));
	
						Distance.add(usb.getDistance());
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}finally{
				this.colse();
			}
		
		return Distance;
	}
	

	public  ArrayList getBestLineByDistance(ArrayList<String> Distance,ArrayList<String> ComLine){
		int a = 0;
		ArrayList al = new ArrayList();
		for(int j = 0;j<Distance.size();j++){
			float BestDistance = 0;
			if(Float.parseFloat(Distance.get(j))>BestDistance){
				BestDistance = Float.parseFloat(Distance.get(j));
				a = j;
			}
		}
		al.add(ComLine.get(a));
		al.add(""+a);
		return al;
	}
	

	public float getDistance2(String ComLine,String EndSta){
		
		ct = new db().getConn();
		float Distance = 0;
		
			try {
				sm = ct.createStatement();
				rs = sm.executeQuery("select Distance from "+SelectSta+" where Name='"+ComLine+"' and  StaName='"+EndSta+"'");
					
					if(rs.next()){
						Distance= Float.parseFloat(rs.getString(1));
					}
				
			} catch (Exception e) {

				e.printStackTrace();
			}finally{
				this.colse();
			}
		
		return Distance;
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
