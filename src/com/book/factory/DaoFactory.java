package com.book.factory;

import com.book.dao.AdminDao;
import com.book.dao.BookDao;
import com.book.dao.CommentDao;
import com.book.dao.RecommendDao;
import com.book.dao.UserDao;

public class DaoFactory {

	public static AdminDao getAdminDaoInstance(){
		return new AdminDao();
	}
	public static BookDao getBookDaoInstance(){
		return new BookDao();
	}
	public static RecommendDao getRecommendDaoInstance(){
		return new RecommendDao();
	}
	public static UserDao getUserDaoInstance(){
		return new UserDao();
	}
	public static CommentDao getCommentDaoInstance(){
		return new CommentDao();
	}
}
