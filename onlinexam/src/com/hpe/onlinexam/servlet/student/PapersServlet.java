package com.hpe.onlinexam.servlet.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.onlinexam.po.Papers;
import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Test;
import com.hpe.onlinexam.service.student.PapersServiceImpl;
import com.hpe.onlinexam.service.teacher.QuestionServiceImpl;
import com.hpe.onlinexam.service.teacher.TestServiceImpl;
import com.hpe.onlinexam.servlet.admin.BaseServlet;
import com.hpe.onlinexam.util.ToolUtil;

@WebServlet("/papersServlet")
public class PapersServlet extends BaseServlet{
	// 注入 test业务类
	TestServiceImpl testService = new TestServiceImpl();
	QuestionServiceImpl questionService = new QuestionServiceImpl();
	PapersServiceImpl paperService = new PapersServiceImpl();
	
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取数据集合
		 // 通过session获取id
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute(ToolUtil.LOGINUSER);
		int stuId  = student.getId();
		List<Map<String, Object>> testList
					= testService.findListByStuId(stuId);
		// 移除 集合中的 数据。
		List removeList = new ArrayList();
		for (Iterator iterator = testList.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			// 过滤 已经考过的试卷，不在页面显示
			int testId = (int) map.get("id");
			List ifhaveList = paperService.getPaperByStudentId(Integer.valueOf(stuId),testId);
			if(ifhaveList.size()>0){
				removeList.add(map);
				continue;
			}
			// 过滤 试卷考试失效已过。
			// 获取 考试开始时间
			Date endDate = (Date)map.get("endDate");
			// 根据 当前时间，计算 剩余时间。   + 考试时长
			long diff = endDate.getTime() + (int)map.get("testTime")*60*1000 - new Date().getTime();
			if(diff < 0){ //为负数，说明，截至时间已到。
				removeList.add(map);
				continue;
			}
		}
		testList.removeAll(removeList); // 从testList中移除已经考过的试卷。
		
		// 转发到 studentlist.jsp页面
		req.setAttribute("testList", testList);
		req.getRequestDispatcher("/student/examlist.jsp").forward(req, resp);
		
	}
	/**
	 * 开始考试学生试卷生成。
	 */
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 获取test试卷id 
		String testId = req.getParameter("id");
		// 根据id查询试卷信息
		Test test =  testService.getTestById(Integer.valueOf(testId));
		// 验证 日期是否过时
		// 获取 考试时间
		Date endDate = (Date)test.getEndDate();
		// 根据 当前时间，计算 剩余时间  + 考试时长。
		int testTime =  test.getTestTime();// 分钟
		int examtime = testTime*60*1000; // 考试时长 毫秒
		// 时间差
		long curtime = new Date().getTime();// 当前时间毫秒
		long starttime = endDate.getTime();// 考试时间 毫秒
		long diff = starttime - curtime + examtime;// 是否属于考试区间
		// 考试已经结束
		if(diff < 0){//返回list首页。
			resp.sendRedirect(req.getContextPath()+"/papersServlet?method=show");
			return;
		}

		// 时间 在 正式考试期间；直接显示考试页面，并且剩余时间计时；否则转发到一个倒计时开始的界面exmaview.jsp。
		// 考试时间的毫秒数 小于 （未到考试时间）进入倒计时。。。。
		if(curtime < starttime){
			// 计算剩余的 天，小时，分，秒 集合。
			Map timeMap = ToolUtil.getDayHourSe(starttime - curtime);
			req.setAttribute("timeMap", timeMap);
			req.setAttribute("testId", testId);
			// 转到倒计时 examview.jsp页面	
			req.getRequestDispatcher("/student/examview.jsp").forward(req, resp);
			return;
		}

		// 计算剩余考试时间。时长 减去 已考时长。（examtime - （curtime - statrtime））
		Map timeMap = ToolUtil.getDayHourSe(examtime - (curtime - starttime));
		req.setAttribute("timeMap", timeMap);
		
		String ques = test.getQuestions(); // 题目主键的字符串 2,3,4,5===> [2,3,4,5]
		// 根据test试卷 questions , 查询题目。
		List queList = questionService.findByIds(ques.split(","));
		
		// 通过session获取当前登录学生的id
		int id = 201406;// 需要修改
		// 回显到 exam.jsp页面
		req.setAttribute("test", test);
		req.setAttribute("queList", queList);
		req.setAttribute("studentId", id);
		req.setAttribute("queListSize", queList.size());
		// 转到exam.jsp页面	
		req.getRequestDispatcher("/student/exam.jsp").forward(req, resp);
	}

	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String courseId = req.getParameter("courseId");
		String scores = req.getParameter("scores");
		String testId = req.getParameter("testId");
		String countTime = req.getParameter("countTime");
		
		// 获取当前所有的试题的主键id 数组。循环遍历数组  id+ "_choice"
		String[] ids = req.getParameterValues("id");
		// 答题选项 封装到map对象中  
		Map<Integer,String> stuAnsMap = new HashMap<Integer,String>();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			String stuAns = req.getParameter(id + "_choice");
			// 把答题的 选项 按照 {key:value}={主键id：选项ABCD} 封装到一个Map对象中。
			stuAnsMap.put(Integer.parseInt(id), stuAns);
			//resp.getWriter().println(i + 1 + "=" + stuAns);
		}
		
		// 根据testID查询试卷信息。或者 表单 提交了所有 question的id。
		// 根据testId 查询 试卷的questions.
		List<Question> questionList = questionService.findByIds(ids);
		// 遍历 questionList中的正确答案，通过stuAnsMap中的答题选项对比。
		// 错误答案的集合
		List worngAns = new ArrayList();
		// 错题主键id的集合
		List worngIds = new ArrayList();
		
		for (Question que : questionList) {
			// 正确答案，和que的主键id； 然后和stuAnsMap对比。
			String stuAns = stuAnsMap.get(que.getId());// 学生的选项
			String ans = que.getAns();	// 标准答案
			if(!ans.equalsIgnoreCase(stuAns)){// 忽略大小写 比对值。
				// 答错的了;把stuAns变量放置到错误答案集合中。
				worngAns.add(stuAns);
				worngIds.add(que.getId());
			}
		}
		
		// 封装到papers实体类中。同时计算分值。
		Papers p = new Papers();
		p.setCourseId(Integer.valueOf(courseId));
		p.setCreateDate(new Date());

		p.setStudentId(201406);// session获取id
		p.setTestId(Integer.valueOf(testId));
		
		// 错题主键集合 字符串类型 “，”号隔开
		String tmp = Arrays.toString(worngIds.toArray());
		p.setWrongQueId(tmp.substring(1, tmp.length() - 1));
		
		// 正确答案集合
		String tmpAns = Arrays.toString(worngAns.toArray());
		p.setWrongAns(tmpAns.substring(1, tmpAns.length() - 1));
		
		p.setTime(ToolUtil.getDateDif(Long.valueOf(countTime)*1000));// 答卷时长
		
		// 获取 试卷 分数 ；获取错题集合； 获取正确答案集合。
		// 总分scores - （总分/试题数）* 错题的个数
		double sco = (Double.valueOf(scores).doubleValue()) -
				Double.valueOf(scores)/ids.length*worngAns.size();
		p.setScore(sco);
		System.out.println(p);
		// 保存到数据，并且在页面显示学生分数；及错题的个数。
		paperService.insertPapers(p);
		
		resp.getWriter().println("学生分值："+ sco + "   <br/> 错题个数: "+ worngAns.size());
	}
	
	
	
	
	
	
	
	
}
