package com.hpe.onlinexam.dao.login;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.util.DBUtil;

public class LoginDaoImpl implements ILoginDao {
	DBUtil dbutil=new DBUtil();
	//管理员登陆
	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		Properties pro=new Properties();
		InputStream inStream= LoginDaoImpl.class.getResourceAsStream("");
		String path = LoginDaoImpl.class.getClassLoader().getResource("db.properties").getPath();
	 String dbname=null;
	 String dbpwd=null;

		try {
			FileReader reader = new FileReader(path);
			pro.load(reader);
			dbname = pro.getProperty("adminuser");
			dbpwd = pro.getProperty("adminpwd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dbname!=null &&dbname.equals(username)&&dbpwd!=null&&dbpwd.equals(password)){
			return username;
		}
		return null;
	}

	@Override
	public Teacher login(Teacher t) {
		// TODO Auto-generated method stub
	
		Teacher teacher=null;
		try {
			teacher=(Teacher)dbutil.getObject(Teacher.class,"select * from teacher where name=? and pwd=?",new Object[]{t.getName(),t.getPwd()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public Student login(Student s) {
		// TODO Auto-generated method stub
		Student student=null;
		try {
			student=(Student)dbutil.getObject(Student.class,"select * from student where name=? and pwd=?",new Object[]{s.getName(),s.getPwd()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void loginOut() {
		// TODO Auto-generated method stub

	}

}
