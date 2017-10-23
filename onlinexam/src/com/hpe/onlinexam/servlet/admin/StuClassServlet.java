package com.hpe.onlinexam.servlet.admin;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.StuClass;
import com.hpe.onlinexam.service.admin.IStuClassService;
import com.hpe.onlinexam.service.admin.StuClassServiceImpl;
/**
 * 班级管理 控制类，增加的请求，修改请求，查询请求。
 * @author Administrator
 *
 */
@WebServlet("/stuClassServlet")
public class StuClassServlet extends HttpServlet{
	private IStuClassService stuClassService 
				= new StuClassServiceImpl();	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 具体的业务处理 ,用户提交的请求中，必须要有一个method参数。
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if(method.equals("show")){
			show(req,resp);
		}else if(method.equals("update")){
			System.out.println("-----------------update");
			update(req,resp);
		}else if(method.equals("showUpdate")){
			showUpdate(req,resp);
		}else if(method.equals("del")){
			delete(req,resp);
		}
	}
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String id = req.getParameter("id");
		stuClassService.deleteStuClass(Integer.parseInt(id));
		resp.sendRedirect("stuClassServlet?method=show");
	}
	// stuClassServlet?method=show
	private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// 查询的业务
		List list = stuClassService.findAll();
		// 控制，转发
		req.setAttribute("stuClassList", list);
		req.getRequestDispatcher("/admin/stuclasslist.jsp").forward(req, resp);
	}
	// stuClassServlet?method=update
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		// 修改的业务
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String deptName = req.getParameter("deptName");
		// 封装前端提交数据
		StuClass stuclass = new StuClass();
		if(id != null){
			stuclass.setId(Integer.valueOf(id));
		}
		stuclass.setName(name);
		stuclass.setDeptName(deptName);
		// 如果id为空，我们执行新增操作，否则我们执行更新。
		if(id == null){
			stuClassService.saveStuClass(stuclass);
		}else{
			stuClassService.updateStuClass(stuclass);
		}
		// 重定向到首页;或者转发到相关页面
		resp.sendRedirect("stuClassServlet?method=show");
	}
	// stuClassServlet?method=showUpdate
	private void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// 根据id，调转到指定页面
		String id = req.getParameter("id");
		int stuid = Integer.valueOf(id);
		// 根据id到数据库中查询 完整的记录StuClass
		StuClass stuClass = stuClassService.findStuClassById(stuid);
		// 把这条数据转发到 stuclassupdate.jsp页面
		req.setAttribute("stuClass", stuClass);// 回显
		req.getRequestDispatcher("/admin/stuclassupdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
	
	
}
