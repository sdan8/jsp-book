package com.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.entity.Admin;
import com.book.factory.DaoFactory;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String info = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		info = request.getParameter("info");
		
		// 管理员登录
		if (info.equals("login")){
			this.login(request, response);
		}
		// 添加管理员
		if (info.equals("add")){
			this.add(request, response);
		}
		// 修改管理员密码
		if (info.equals("psw")){
			this.psw(request, response);
		}
		// 退出登录
		if (info.equals("exit")){
			this.exit(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	// 管理员登录
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		Admin adminData = new Admin();
		adminData.setAdminName(adminName);
		adminData.setAdminPassword(adminPassword);
		Admin admin = DaoFactory.getAdminDaoInstance().login(adminData);
		if (admin != null){
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			session.setAttribute("admin_login_flag", true);
			request.getRequestDispatcher("manager.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "登录失败");
			request.setAttribute("message_flag", true);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		Admin adminData = new Admin();
		adminData.setAdminName(adminName);
		adminData.setAdminPassword(adminPassword);
		if (DaoFactory.getAdminDaoInstance().add(adminData)){
			request.setAttribute("message", "添加成功！");
		} else {
			request.setAttribute("message", "添加失败！");
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("admin/addAdmin.jsp").forward(request, response);
	}
	protected void psw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		int adminId = admin.getAdminId();
		String adminPassword = admin.getAdminPassword();
		
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		System.out.println(adminPassword);
		if (!password.equals(adminPassword)){
			request.setAttribute("message", "原密码错误！");
		} else {
			Admin adminData = new Admin();
			adminData.setAdminId(adminId);
			adminData.setAdminPassword(newPassword);
			
			if (DaoFactory.getAdminDaoInstance().update(adminData)){
				request.setAttribute("message", "密码修改成功！");
				admin.setAdminPassword(newPassword);
				session.setAttribute("admin", admin);
			} else {
				request.setAttribute("message", "密码修改失败！");
			}
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("admin/psw.jsp").forward(request, response);
	}
	protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		session.setAttribute("admin_login_flag", false);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
}
