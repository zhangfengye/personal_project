package com.hpe.onlinexam.servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.service.teacher.ClassEvalutionServiceImpl;
import com.hpe.onlinexam.servlet.admin.BaseServlet;
import com.hpe.onlinexam.vo.PapersView;
import com.hpe.onlinexam.vo.TCView;
@WebServlet("/classEvaluationServlet")
public class ClassEvaluationServlet extends BaseServlet {
	ClassEvalutionServiceImpl cesi=new ClassEvalutionServiceImpl();
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String courseName = req.getParameter("courseName");
		String className = req.getParameter("className");
		
		// 从数据库服务器中获取数据，转发到 viewlist.jsp页面
		PapersView pv = new PapersView();
		
		pv.setCourseName(courseName);
		pv.setClassName(className);
		
		
		List<PapersView> papersList =  cesi.findClassEvalution(pv);// v
		req.setAttribute("paperslist", papersList);
		req.setAttribute("papersview", pv);
		
		req.getRequestDispatcher("/teacher/classevaluationlist.jsp").forward(req, resp);
	}
}
