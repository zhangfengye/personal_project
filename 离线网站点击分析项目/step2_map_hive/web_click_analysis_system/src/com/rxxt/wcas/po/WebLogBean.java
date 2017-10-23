package com.rxxt.wcas.po;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import com.rxxt.wcas.util.TransUtil;


/**
 * �Խ��ⲿ���ݵĲ㣬��ṹ������ø��ⲿ����Դ����һ��
 * ��� ��Դ��
 * @author
 *
 */
public class WebLogBean implements WritableComparable<WebLogBean> {

	private boolean valid = true;// �ж������Ƿ�Ϸ�
	private String remote_addr;// ��¼�ͻ��˵�ip��ַ
	private String remote_user;// ��¼�ͻ����û�����,��������"-"
	private String time_local;// ��¼����ʱ����ʱ��
	/**
	 * �û������url��httpЭ�飬��Ϊ��̬�ͷǾ�̬������Ǿ�̬������ҵ����һ��Ҳ��ͳ�ƣ�������Ϊ�ǷǷ����ݡ�
	 * һ������������Ϊ�ǷǾ�̬�����󣬼��Ϸ�������
	 * "/about"
		"/black-ip-list/"
		"/cassandra-clustor/"
		"/finance-rhive-repurchase/"
		"/hadoop-family-roadmap/"
		"/hadoop-hive-intro/"
		"/hadoop-zookeeper-intro/"
		"/hadoop-mahout-roadmap/"
	 */
	private String request;// ��¼�����url��httpЭ�飬
	private String status;// ��¼����״̬���ɹ���200
	private String body_bytes_sent;// ��¼���͸��ͻ����ļ��������ݴ�С
	private String http_referer;// ������¼���Ǹ�ҳ�����ӷ��ʹ�����
	private String http_user_agent;// ��¼�ͻ�������������Ϣ

	
	public void set(boolean valid,String remote_addr, String remote_user, String time_local, String request, String status, String body_bytes_sent, String http_referer, String http_user_agent) {
		this.valid = valid;
		this.remote_addr = remote_addr;
		this.remote_user = remote_user;
		this.time_local = time_local;
		this.request = request;
		this.status = status;
		this.body_bytes_sent = body_bytes_sent;
		this.http_referer = http_referer;
		this.http_user_agent = http_user_agent;
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

	public String getTime_local() {
		return this.time_local;
	}

	public void setTime_local(String time_local) {
		this.time_local = time_local;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBody_bytes_sent() {
		return body_bytes_sent;
	}

	public void setBody_bytes_sent(String body_bytes_sent) {
		this.body_bytes_sent = body_bytes_sent;
	}

	public String getHttp_referer() {
		return http_referer;
	}

	public void setHttp_referer(String http_referer) {
		this.http_referer = http_referer;
	}

	public String getHttp_user_agent() {
		return http_user_agent;
	}

	public void setHttp_user_agent(String http_user_agent) {
		this.http_user_agent = http_user_agent;
	}
	//�Զ��巽���������ж������Ƿ�Ϸ�
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.valid);
		sb.append("\001").append(this.getRemote_addr());
		sb.append("\001").append(this.getRemote_user());
		sb.append("\001").append(this.getTime_local());
		sb.append("\001").append(this.getRequest());
		sb.append("\001").append(this.getStatus());
		sb.append("\001").append(this.getBody_bytes_sent());
		sb.append("\001").append(this.getHttp_referer());
		sb.append("\001").append(this.getHttp_user_agent());
		return sb.toString();
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.valid = in.readBoolean();
		this.remote_addr = in.readUTF();
		this.remote_user = in.readUTF();
		this.time_local = in.readUTF();
		this.request = in.readUTF();
		this.status = in.readUTF();
		this.body_bytes_sent = in.readUTF();
		this.http_referer = in.readUTF();
		this.http_user_agent = in.readUTF();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeBoolean(this.valid);
		out.writeUTF(null==remote_addr?"":remote_addr);
		out.writeUTF(null==remote_user?"":remote_user);
		out.writeUTF(null==time_local?"":time_local);
		out.writeUTF(null==request?"":request);
		out.writeUTF(null==status?"":status);
		out.writeUTF(null==body_bytes_sent?"":body_bytes_sent);
		out.writeUTF(null==http_referer?"":http_referer);
		out.writeUTF(null==http_user_agent?"":http_user_agent);

	}

	@Override
	public int compareTo(WebLogBean o) {
          return (int) TransUtil.timeDiff(o.getTime_local(), this.getTime_local());
	}

}
