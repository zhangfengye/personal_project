package com.rxxt.wcas.hive;

import java.util.Properties;

import com.rxxt.wcas.util.DBUtil;
import com.rxxt.wcas.util.GetConfigUtil;
import com.rxxt.wcas.util.TransUtil;

public class ExecStatis {
public static void step6() throws Exception {
	Properties config = GetConfigUtil.getPathConfig();
	String date = TransUtil.getDate();
	DBUtil db = new DBUtil();
	System.out.println(db.execute(SqlStringPVStatis.DROP_DW_PVS_HOUR,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.CRE_DW_PVS_HOUR,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.INSERT_DW_PVS_HOUR,new Object[]{date,date}));
	System.out.println(db.execute(SqlStringPVStatis.DROP_DW_PVS_DAY,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.CRE_DW_PVS_DAY,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.INSERT_DW_PVS_DAY,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.DROP_DW_PVS_MONTH,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.CRE_DW_PVS_MONTH,new Object[]{}));
	System.out.println(db.execute(SqlStringPVStatis.INSERT_DW_PVS_MONTH,new Object[]{}));
}
}
