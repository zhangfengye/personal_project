package com.rxxt.wcas.po;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class PageViewsBean implements WritableComparable<PageViewsBean> {
/**
 * 98c3f45b-53d6-4389-8df8-84a6955cd82a
 * 111.193.224.9
 * -
 * 2013-09-18 07:17:25
 * /hadoop-family-roadmap/
 * 1
 * 60
 * "https://www.google.com.hk/"
 * "Mozilla/5.0(Macintosh;IntelMacOSX10_8_5)AppleWebKit/537.36(KHTML,likeGecko)Chrome/29.0.1547.57Safari/537.36"
 * 11715
 * 200
 */
	private String session;
	private String remote_addr;
	private String remote_user;
	private String timestr; //开始访问的时间
	private String request;  //访问的页面
	private int step;
	private String staylong;  //停留时长
	private String referal;   //从从那个页面链接访问过来的
	private String useragent;  //用户浏览器信息
	private String bytes_send;  //返回给客户的字节数
	private String status;  //状态码

	

	public void set(String session, String remote_addr, String remote_user, String timestr, String request,
			int step, String staylong, String referal, String useragent, String bytes_send, String status) {
		
		this.session = session;
		this.remote_addr = remote_addr;
		this.remote_user = remote_user;
		this.timestr = timestr;
		this.request = request;
		this.step = step;
		this.staylong = staylong;
		this.referal = referal;
		this.useragent = useragent;
		this.bytes_send = bytes_send;
		this.status = status;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getRemote_addr() {
		return remote_addr;
	}

	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}

	public String getRemote_user() {
		return remote_user;
	}

	public void setRemote_user(String remote_user) {
		this.remote_user = remote_user;
	}

	public String getTimestr() {
		return timestr;
	}

	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getStaylong() {
		return staylong;
	}

	public void setStaylong(String staylong) {
		this.staylong = staylong;
	}

	public String getReferal() {
		return referal;
	}

	public void setReferal(String referal) {
		this.referal = referal;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public String getBytes_send() {
		return bytes_send;
	}

	public void setBytes_send(String bytes_send) {
		this.bytes_send = bytes_send;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.session = in.readUTF();
		this.remote_addr = in.readUTF();
		this.remote_user=in.readUTF();
		this.timestr = in.readUTF();
		this.request = in.readUTF();
		this.step = in.readInt();
		this.staylong = in.readUTF();
		this.referal = in.readUTF();
		this.useragent = in.readUTF();
		this.bytes_send = in.readUTF();
		this.status = in.readUTF();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(session);
		out.writeUTF(remote_addr);
		out.writeUTF(remote_user);
		out.writeUTF(timestr);
		out.writeUTF(request);
		out.writeInt(step);
		out.writeUTF(staylong);
		out.writeUTF(referal);
		out.writeUTF(useragent);
		out.writeUTF(bytes_send);
		out.writeUTF(status);

	}

	@Override
	public int compareTo(PageViewsBean o) {
		// TODO Auto-generated method stub
		
		return o.getStep()-this.getStep();
	}

}
