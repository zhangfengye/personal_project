package com.rxxt.wcas.other;


import com.rxxt.wcas.util.DBUtil2;
import com.rxxt.wcas.util.TransUtil;

public class ExecAimSql {
public static void step7() {
	String date = TransUtil.getDate();
	DBUtil2 db2 = new DBUtil2();
	try {
		System.out.println(db2.execute(AimSqlString.DROP_DW_PVS_MONTH,new Object[]{}));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("表不存在");
	}
	try {
		System.out.println(db2.execute(AimSqlString.DROP_DW_PVS_DAY,new Object[]{}));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		
	}
	try {
		System.out.println(db2.execute(AimSqlString.DROP_DW_PVS_HOUR,new Object[]{}));
	} catch (Exception e) {
		System.out.println("表不存在");
	}
	try {
		System.out.println(db2.execute(AimSqlString.CRE_DW_PVS_MONTH,new Object[]{}));
		System.out.println(db2.execute(AimSqlString.CRE_DW_PVS_DAY,new Object[]{}));
		System.out.println(db2.execute(AimSqlString.CRE_DW_PVS_HOUR,new Object[]{}));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
