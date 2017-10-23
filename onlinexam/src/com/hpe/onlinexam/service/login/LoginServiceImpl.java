package com.hpe.onlinexam.service.login;

import com.hpe.onlinexam.dao.login.LoginDaoImpl;
import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Teacher;

public class LoginServiceImpl implements ILoginService {
LoginDaoImpl ldi=new LoginDaoImpl();
	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		return ldi.login(username, password);
	}

	@Override
	public Teacher login(Teacher t) {
		// TODO Auto-generated method stub
		return ldi.login(t);
	}

	@Override
	public Student login(Student s) {
		// TODO Auto-generated method stub
		return ldi.login(s);
	}

	@Override
	public void loginOut() {
		// TODO Auto-generated method stub
    
	}

}
