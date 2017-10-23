package com.rxxt.wcas.other;

public class AimSqlString {
	public static final String DROP_DW_PVS_HOUR="drop table dw_pvs_hour";
	public static final String CRE_DW_PVS_HOUR="create table dw_pvs_hour(month varchar(255),day varchar(255),hour varchar(255),pvs bigint,part varchar(255))partition by key (part)";
	
	public static final String DROP_DW_PVS_DAY="drop table dw_pvs_day";
	public static final String CRE_DW_PVS_DAY="create table dw_pvs_day(pvs bigint,month varchar(255),day varchar(255))";
	
	public static final String DROP_DW_PVS_MONTH="drop table dw_pvs_month";
	public static final String CRE_DW_PVS_MONTH="create table dw_pvs_month(pvs bigint,month varchar(255))";
}
