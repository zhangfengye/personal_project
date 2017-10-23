package com.rxxt.wcas.hive;

import java.util.Properties;

import com.rxxt.wcas.hive.SqlStringODS;
import com.rxxt.wcas.util.DBUtil;
import com.rxxt.wcas.util.GetConfigUtil;
import com.rxxt.wcas.util.TransUtil;

public class ExecODS {
	public static void step5() throws Exception {
		Properties config = GetConfigUtil.getPathConfig();
		String date = TransUtil.getDate();
		DBUtil db = new DBUtil();
		System.out.println(db.execute(SqlStringODS.DROP_CLICK_STREAM_VISIT, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.DROP_DIM_TIME, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.DROP_ODS_CLICK_PAGEVIEWS, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.DROP_ODS_WEBLOG_ORIGIN, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.CRE_ODS_WEBLOG_ORIGIN, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.CRE_ODS_CLICK_PAGEVIEWS, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.CRE_CLICK_STREAM_VISIT, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.LOAD_ODS_WEBLOG_ORIGIN,
				new Object[] { config.getProperty("target_path2_hp") + date + "/", date }));
		System.out.println(db.execute(SqlStringODS.LOAD_ODS_CLICK_PAGEVIEWS,
				new Object[] { config.getProperty("target_path3_hp") + date + "/", date }));
		System.out.println(db.execute(SqlStringODS.LOAD_CLICK_STREAM_VISIT,
				new Object[] { config.getProperty("target_path4_hp") + date + "/", date }));
		System.out.println(db.execute(SqlStringODS.DROP_ODS_WEBLOG_DETAIL, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.CRE_ODS_WEBLOG_DETAIL, new Object[] {}));
		System.out.println(db.execute(SqlStringODS.INSERT_ODS_WEBLOG_DETAIL, new Object[] { date }));

	}
}
