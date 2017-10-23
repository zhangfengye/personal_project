package com.rxxt.wcas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;




public class DBUtil2 {
	public static String DRIVER;
	public static String URL;
	public static String USER;
	public static String PWD;
   static {
	   Properties config =GetConfigUtil.getDBConfig2();
		DRIVER = config.getProperty("drivername");
		URL = config.getProperty("url");
		USER = config.getProperty("username");
		PWD	= config.getProperty("password");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
   public static Connection getConnectionJdbc() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("chenggong ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
   private void setPreparedStatementParam(PreparedStatement pstmt, Object[] paramList) throws Exception {
		if (pstmt == null || paramList == null) {
			return;
		}
		DateFormat df = DateFormat.getDateTimeInstance();
		for (int i = 0; i < paramList.length; i++) {
			// -
			if (paramList[i] instanceof Integer) {
				int paramValue = ((Integer) paramList[i]).intValue();
				pstmt.setInt(i + 1, paramValue);
			} else if (paramList[i] instanceof Float) {
				float paramValue = ((Float) paramList[i]).floatValue();
				pstmt.setFloat(i + 1, paramValue);
			} else if (paramList[i] instanceof Double) {
				double paramValue = ((Double) paramList[i]).doubleValue();
				pstmt.setDouble(i + 1, paramValue);
			} else if (paramList[i] instanceof Date) {
				pstmt.setString(i + 1, df.format((Date) paramList[i]));
			} else if (paramList[i] instanceof Long) {
				long paramValue = ((Long) paramList[i]).longValue();
				pstmt.setLong(i + 1, paramValue);
			} else if (paramList[i] instanceof String) {
				pstmt.setString(i + 1, (String) paramList[i]);
			}

			// = pstmt.setObject(i + 1, paramList[i]);
		}
		return;
	}
   private static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
		if (conn == null || sql == null || sql.trim().equals("")) {
			return null;
		}
		PreparedStatement pstmt = conn.prepareStatement(sql.trim());
		return pstmt;
	}
   public boolean execute(String sql, Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			return false;
		}
		Connection conn=getConnectionJdbc();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = DBUtil2.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return false;
			}
			pstmt.execute();
		} catch (Exception e) {
		
			throw new Exception(e);
		} finally {
			closeStatement(pstmt);
			closeConn(conn);
		}

		return true;
	}
   /**
	 * 关闭数据库
	 * 
	 * @param conn
	 */
	private void closeConn(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}

	/**
	 * 关闭
	 * 
	 * @param stmt
	 */
	private void closeStatement(Statement stmt) {
		if (stmt == null) {
			return;
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			
		}
	}

	/**
	 * 关闭
	 * 
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			
		}
	}
}
