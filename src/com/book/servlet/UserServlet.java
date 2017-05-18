package com.book.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.entity.User;
import com.book.factory.DaoFactory;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig servletConfig;
       
	private String info = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.servletConfig = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		info = request.getParameter("info");
		
		if (info.equals("signup")){
			this.signup(request, response);
		}
		if (info.equals("login")){
			this.login(request, response);
		}
		if (info.equals("exit")){
			this.exit(request, response);
		}
		if (info.equals("psw")){
			this.psw(request, response);
		}
		if (info.equals("avatar")){
			this.avatar(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userPassword2 = request.getParameter("userPassword2");
		
		if (userPassword.equals(userPassword2)){
			User userData = new User();
			userData.setUserName(userName);
			userData.setUserPassword(userPassword);
			if (DaoFactory.getUserDaoInstance().add(userData)){
				request.setAttribute("message", "注册成功！");
			} else {
				request.setAttribute("message", "注册失败！");
			}
		} else {
			request.setAttribute("message", "两次密码输入不一致！");
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		
		User userData = new User();
		userData.setUserName(userName);
		userData.setUserPassword(userPassword);
		User user = DaoFactory.getUserDaoInstance().login(userData);
		if (user != null){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("login_flag", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "登录失败！");
			request.setAttribute("message_flag", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.setAttribute("login_flag", false);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void psw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userPassword = user.getUserPassword();
		
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		if (password.equals(userPassword)){
			User userData = new User();
			userData.setUserPassword(newPassword);
			userData.setUserId(user.getUserId());
			
			request.setAttribute("message", DaoFactory.getUserDaoInstance().updatePsw(userData) ? "密码修改成功！" : "密码修改失败！");
			user.setUserPassword(newPassword);
			session.setAttribute("user", user);
		} else {
			request.setAttribute("message", "原密码错误！");
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("user/psw.jsp").forward(request, response);
	}
	protected void avatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();
			//上传初始化
			su.initialize(servletConfig, request, response);
			//限制每个上传文件的最大长度
			su.setMaxFileSize(1000000);
			//限制总上传数据的长度
//			su.setTotalMaxFileSize(200000);
			//设定允许上传的文件（通过扩展名限制），仅允许jpg，png文件
//			su.setAllowedFilesList("jpg,png");
			//设定禁止上传的文件（通过扩展名限制），禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件
			su.setDeniedFilesList("exe,bat,jsp,htm,html");
			//上传文件
			su.upload();
			//获取上传的文件操作
			Files files = su.getFiles();
			//获取单个文件
			File singleFile = files.getFile(0);
			//获取上传文件的扩展名
			String fileType = singleFile.getFileExt();
			//设置上传文件的扩展名
			String[] type = {"JPG","jpg"};
			// 判断上传文件的类型是否正确
			int place = java.util.Arrays.binarySearch(type, fileType);
			//判断文件扩展名是否正确
			if (place != -1){
				//判断该文件是否被选择
				if (!singleFile.isMissing()){
					
					//以系统时间作为上传文件名称，设置上传完整路径
					String fileName = String.valueOf(System.currentTimeMillis());
					String filedir = "img/" + fileName + "." + singleFile.getFileExt();
					// 带后缀名保存与数据库中
					String saveFileName = fileName + "." + singleFile.getFileExt();
					
					//执行上传操作
					singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);
					System.out.println("上传至： " + filedir);
					
					// 封装
					User userData = new User();
					userData.setUserId(Integer.parseInt(su.getRequest().getParameter("userId")));
					userData.setUserAvatar(saveFileName);
					
					if (DaoFactory.getUserDaoInstance().updateAvatar(userData)){
						request.setAttribute("message", "修改头像成功！");
					} else {
						request.setAttribute("message", "修改头像失败！");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("修改头像Servlet异常！", e);
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("user/avatar.jsp").forward(request, response);
	}
}
