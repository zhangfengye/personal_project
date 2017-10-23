package com.rxxt.wcas;

import java.io.IOException;
import java.net.URISyntaxException;

import com.rxxt.wcas.hive.ExecODS;
import com.rxxt.wcas.hive.ExecStatis;
import com.rxxt.wcas.mapreduce.clickstream.ClickStreamPageViews;
import com.rxxt.wcas.mapreduce.clickstream.ClickStreamVisit;
import com.rxxt.wcas.mapreduce.pre.WeblogPreProcess;
import com.rxxt.wcas.other.ExecAimSql;
import com.rxxt.wcas.preworkdir.PreWorkDir;

public class Test {
public static void main(String[] args) throws Exception {
	Thread.sleep(60000);
	PreWorkDir.step1();
	WeblogPreProcess.step2();
	ClickStreamPageViews.step3();
	ClickStreamVisit.step4();
	ExecODS.step5();
	ExecStatis.step6();
	ExecAimSql.step7();
}
}
