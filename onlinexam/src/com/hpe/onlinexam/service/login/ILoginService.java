package com.hpe.onlinexam.service.login;

import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Teacher;

public interface ILoginService {
	//管理员登陆  读取配置文件
		String login(String username,String password);
		//teacher 登陆
		Teacher login(Teacher t);
		//学生登陆
		Student login(Student s);
		//退出登陆  清理数据库
		void loginOut();
}
