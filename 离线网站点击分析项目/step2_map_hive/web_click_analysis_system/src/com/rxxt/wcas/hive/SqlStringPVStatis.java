package com.rxxt.wcas.hive;

public class SqlStringPVStatis {
public static final String DROP_DW_PVS_HOUR="drop table dw_pvs_hour";
public static final String CRE_DW_PVS_HOUR="create table dw_pvs_hour(month string,day string,hour string,pvs bigint) partitioned by(part string)";
public static final String INSERT_DW_PVS_HOUR="insert into table dw_pvs_hour partition(part=?)"
		+ "select a.month as month,a.day as day,a.hour as hour,count(*) as pvs from ods_weblog_detail a"
		+ " where  a.part=?  group by a.month,a.day,a.hour";
public static final String DROP_DW_PVS_DAY="drop table dw_pvs_day";
public static final String CRE_DW_PVS_DAY="create table dw_pvs_day(pvs bigint,month string,day string)";
public static final String INSERT_DW_PVS_DAY="insert into table dw_pvs_day "
		+ "Select count(*) as pvs,month,day from dw_pvs_hour group by month,day";
public static final String DROP_DW_PVS_MONTH="drop table dw_pvs_month";
public static final String CRE_DW_PVS_MONTH="create table dw_pvs_month(pvs bigint,month string)";
public static final String INSERT_DW_PVS_MONTH="insert into table dw_pvs_month "
		+ "select count(*) as pvs,month from dw_pvs_day group by month";
}
