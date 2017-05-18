package com.book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.entity.Book;
import com.book.entity.Comment;
import com.book.entity.User;
import com.book.factory.DaoFactory;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String info = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		info = request.getParameter("info");
		
		if (info.equals("review")){
			this.review(request, response);
		}
		if (info.equals("add")){
			this.add(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = DaoFactory.getBookDaoInstance().findBook(bookId);
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("newReview.jsp").forward(request, response);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		
		String commentTitle = request.getParameter("commentTitle");
		String commentText = request.getParameter("commentText");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		Comment commentData = new Comment();
		commentData.setUserId(userId);
		commentData.setBookId(bookId);
		commentData.setCommentTitle(commentTitle);
		commentData.setCommentText(commentText);
		
		request.setAttribute("message", DaoFactory.getCommentDaoInstance().save(commentData) ? "评论成功！" : "评论失败！");
		request.setAttribute("message_flag", true);
		request.getRequestDispatcher("ReviewServlet?info=review&id=" + bookId).forward(request, response);
	}
}
