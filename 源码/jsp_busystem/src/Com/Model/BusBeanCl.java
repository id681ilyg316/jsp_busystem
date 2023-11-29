package Com.Model;

import java.sql.*;
import java.util.ArrayList;

import Com.db.db;

public class BusBeanCl {
	private Connection ct = null;
	private Statement sm = null;
	private ResultSet rs = null;

	int pageNow = 0;
	int pageCount = 0;
	int rowCount = 0;
	int pageSize = 10;


	public synchronized boolean delBusInfo(String BusId) {
		boolean b = false;
		try {

			ct = new db().getConn();
			sm = ct.createStatement();
			int temp = sm.executeUpdate("delete  from Bus where ID='" + BusId + "'");


			if (temp == 1) {

				b = true;

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}

		return b;
	}


	public synchronized boolean delAllBusInfo(String[] BusId) {
		boolean b = false;
		try {

			ct = new db().getConn();
			ct.setAutoCommit(false);
			sm = ct.createStatement();
			for (int i = 0; i < BusId.length; i++) {

				sm.addBatch("delete  from Bus where ID='" + BusId[i] + "'");
			}
			int temp[] = sm.executeBatch();


			if (temp.length > 0) {

				b = true;
				ct.commit();
			}

		} catch (Exception e) {

			try {
				ct.rollback();
			} catch (SQLException e1) {

				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.colse();
		}

		return b;
	}


	public boolean AddInfo(String BusId, String BusName, String Region, String BeginTime, String LastTime) {
		boolean a = false;
		try {
			ct = new db().getConn();
			sm = ct.createStatement();

			int i = sm.executeUpdate("insert into Bus(ID,Name,Region,BeginTime,LastTime) values('" + BusId + "','"
					+ BusName + "','" + Region + "','" + BeginTime + "','" + LastTime + "')");
			if (i == 1) {
				a = true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}
		return a;
	}


	public boolean ReviseInfo(String BusId, String BusName, String Region, String BeginTime, String LastTime) {
		boolean a = false;
		try {
			ct = new db().getConn();
			sm = ct.createStatement();
			int i = sm.executeUpdate("update Bus set Name='" + BusName + "', Region='" + Region + "', BeginTime='"
					+ BeginTime + "', LastTime='" + LastTime + "' where ID='" + BusId + "'");
			if (i == 1) {
				a = true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}
		return a;
	}


	public int getPageCountBySQL(String sql) {
		try {
			ct = new db().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}
		return pageCount;
	}


	public int getPageCount() {
		try {
			ct = new db().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery("select count(*) from Bus");
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}
		return pageCount;
	}


	public ArrayList getBusinfoByPage(int pageNow) {
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Bus limit " + pageSize * (pageNow - 1) + "," + pageSize);

			while (rs.next()) {
				BusBean bb = new BusBean();
				bb.setID(rs.getString(1));
				bb.setBusName(rs.getString(2));
				bb.setRegion(rs.getString(3));
				bb.setBeginTime(rs.getString(4));
				bb.setLastTime(rs.getString(5));

				al.add(bb);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}

		return al;
	}


	public ArrayList getBusinfobysql(String sql) {
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);

			while (rs.next()) {
				BusBean bb = new BusBean();
				bb.setID(rs.getString(1));
				bb.setBusName(rs.getString(2));
				bb.setRegion(rs.getString(3));
				bb.setBeginTime(rs.getString(4));
				bb.setLastTime(rs.getString(5));

				al.add(bb);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}

		return al;
	}


	public ArrayList getBusinfoByBusID(String BusId) {
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Bus where ID = '" + BusId + "' limit 0,1");

			if (rs.next()) {
				BusBean bb = new BusBean();
				bb.setID(rs.getString(1));
				bb.setBusName(rs.getString(2));
				bb.setRegion(rs.getString(3));
				bb.setBeginTime(rs.getString(4));
				bb.setLastTime(rs.getString(5));

				al.add(bb);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}

		return al;
	}


	public ArrayList getBusinfo() {
		ArrayList al = new ArrayList();
		ct = new db().getConn();
		try {
			sm = ct.createStatement();
			rs = sm.executeQuery("select * from Bus ");

			while (rs.next()) {
				BusBean bb = new BusBean();
				bb.setID(rs.getString(1));
				bb.setBusName(rs.getString(2));
				bb.setRegion(rs.getString(3));
				bb.setBeginTime(rs.getString(4));
				bb.setLastTime(rs.getString(5));

				al.add(bb);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			this.colse();
		}

		return al;
	}

	public void colse() {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
