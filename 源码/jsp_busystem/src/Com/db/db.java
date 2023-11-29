package Com.db;

import java.sql.*;

public class db {

	private Connection ct = null;
	public final static String driverName = "com.mysql.cj.jdbc.Driver";
	public final static String url = "jdbc:mysql://www.icodedock.com/busystem?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
	public final static String user = "busystem";
	public final static String pwd = "busystem";

	public Connection getConn() {

		try {
			Class.forName(driverName);
			ct = DriverManager.getConnection(url, user, pwd);
			Statement sm = ct.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}
}
