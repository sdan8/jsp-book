package com.book.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Book;
import com.book.entity.Comment;
import com.book.factory.DaoFactory;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig servletConfig;
	
	String info = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
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
		
		// 添加书籍
		if (info.equals("add")){
			this.add(request, response);
		}
		// 查看所有书籍
		if (info.equals("showAll")){
			this.show_all(request, response);
		}
		// 推荐书籍
		if (info.equals("rcmd")){
			this.recommend(request, response);
		}
		if (info.equals("book")){
			this.book(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					
					
					String bookTitle = su.getRequest().getParameter("bookTitle");
					String bookAuthor = su.getRequest().getParameter("bookAuthor");
					String bookPub = su.getRequest().getParameter("bookPub");
					String bookPubDateString = su.getRequest().getParameter("bookPubDate");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date bookPubDate = sdf.parse(bookPubDateString);
					String bookContent = su.getRequest().getParameter("bookContent");
					int bookCate = Integer.parseInt(su.getRequest().getParameter("bookCate"));
					
					// 封装
					Book bookData = new Book();
					bookData.setBookTitle(bookTitle);
					bookData.setBookAuthor(bookAuthor);
					bookData.setBookPub(bookPub);
					bookData.setBookPubDate(bookPubDate);
					bookData.setBookContent(bookContent);
					bookData.setBookCate(bookCate);
					bookData.setBookPic(saveFileName);
					
					if (DaoFactory.getBookDaoInstance().add(bookData)){
						request.setAttribute("message", "添加成功！");
					} else {
						request.setAttribute("message", "添加失败！");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("添加书籍Servlet异常！", e);
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("admin/addBook.jsp").forward(request, response);
	}
	// 显示所有书籍
	protected void show_all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> list = DaoFactory.getBookDaoInstance().findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("admin/showBook.jsp").forward(request, response);
	}
	// 推荐书籍
	protected void recommend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		Book bookData = new Book();
		bookData.setBookId(bookId);
		
		if (DaoFactory.getRecommendDaoInstance().add(bookData)){
			request.setAttribute("message", "推荐成功！");
		} else {
			request.setAttribute("message", "推荐失败！");
		}
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("admin/recommend.jsp").forward(request, response);
	}
	protected void book(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		
		Book book = DaoFactory.getBookDaoInstance().findBook(bookId);
		List<Comment> commentList = DaoFactory.getCommentDaoInstance().findByBook(bookId);
		
		request.setAttribute("book", book);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("book.jsp").forward(request, response);
	}
}
