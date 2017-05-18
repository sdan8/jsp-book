package com.book.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Book;
import com.book.factory.DaoFactory;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String info = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		info = request.getParameter("info");
		
		if (info.equals("index")){
			this.index(request, response);
		}
		if (info.equals("cate")){
			this.cate(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> rcmdList = DaoFactory.getRecommendDaoInstance().findAll();
		List<Book> cate1 = DaoFactory.getBookDaoInstance().findAllByCate(1);
		List<Book> cate2 = DaoFactory.getBookDaoInstance().findAllByCate(2);
		List<Book> cate3 = DaoFactory.getBookDaoInstance().findAllByCate(3);
		List<Book> cate4 = DaoFactory.getBookDaoInstance().findAllByCate(4);
		
		request.setAttribute("cate1", cate1);
		request.setAttribute("cate2", cate2);
		request.setAttribute("cate3", cate3);
		request.setAttribute("cate4", cate4);
		request.setAttribute("rcmdList", rcmdList);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
	
	protected void cate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookCate = Integer.parseInt(request.getParameter("id"));
		
		List<Book> bookList = DaoFactory.getBookDaoInstance().findAllByCate(bookCate);
		request.setAttribute("bookList", bookList);
		request.setAttribute("id", bookCate);
		request.getRequestDispatcher("cate.jsp").forward(request, response);
	}
}
