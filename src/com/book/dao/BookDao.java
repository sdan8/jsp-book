package com.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.book.entity.Book;
import com.book.util.JdbcUtil;


public class BookDao {

	/**
	 * 添加书籍
	 * @param book
	 * @return
	 */
	public boolean add(Book book){
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "INSERT INTO book(bookTitle, bookAuthor, bookPub, bookPubDate, bookContent, bookCate, bookPic) VALUES(?,?,?,?,?,?,?)";
		JdbcUtil jdbc = null;
		
		String bookTitle = book.getBookTitle();
		String bookAuthor = book.getBookAuthor();
		String bookPub = book.getBookPub();
		Date bookPubDate = book.getBookPubDate();
		String bookContent = book.getBookContent();
		int bookCate = book.getBookCate();
		String bookPic = book.getBookPic();
		
		if (bookTitle != null && !"".equals(bookTitle)){
			params.add(bookTitle);
		} else {
			return result;
		}
		if (bookAuthor != null && !"".equals(bookAuthor)){
			params.add(bookAuthor);
		} else {
			return result;
		}if (bookPub != null && !"".equals(bookPub)){
			params.add(bookPub);
		} else {
			return result;
		}
		params.add(bookPubDate);
		if (bookContent != null && !"".equals(bookContent)){
			params.add(bookContent);
		} else {
			return result;
		}
		params.add(bookCate);
		if (bookPic != null && !"".equals(bookPic)){
			params.add(bookPic);
		} else {
			return result;
		}
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			if (jdbc.updateByPreparedStatement(sql, params)){
				// 如果保存成功则返回结果为true
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("保存书籍异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
		return result;
	}
	
	/**
	 * 返回所有书籍
	 * @return
	 */
	public List<Book> findAll(){

		// 存放查询参数
		List<Object> paramList = new ArrayList<Object>();
		
		String sql = "SELECT bookId, bookTitle, bookAuthor, bookPub, bookPubDate, bookContent, bookCate, bookPic FROM book WHERE 1=1";
		
		// 存放所有查询出的图书对象
		List<Book> bookList = new ArrayList<Book>();
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			
			// 获取总记录数
			List<Map<String, Object>> queryResultList = jdbcUtil.findResult(sql, paramList);
			if (queryResultList != null){
				for (Map<String, Object> map : queryResultList) {
					Book s = new Book(map);
					bookList.add(s);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("查询所有图书异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		return bookList;
	}
	
	/**
	 * 根据分类返回所有书籍
	 * @param cateId
	 * @return
	 */
	public List<Book> findAllByCate(int cateId){
		
		List<Object> paramList = new ArrayList<Object>();
		
		String sql = "SELECT bookId, bookTitle, bookAuthor, bookPub, bookPubDate, bookContent, bookCate, bookPic FROM book WHERE bookCate=?";
		
		paramList.add(cateId);
		// 存放所有查询出的图书对象
		List<Book> bookList = new ArrayList<Book>();
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			
			// 获取总记录数
			List<Map<String, Object>> queryResultList = jdbcUtil.findResult(sql, paramList);
			if (queryResultList != null){
				for (Map<String, Object> map : queryResultList) {
					Book s = new Book(map);
					bookList.add(s);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("查询所有分类图书异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		return bookList;
	}
	public Book findBook(int bookId){
		List<Object> paramList = new ArrayList<Object>();
		Book b = null;
		String sql = "SELECT bookId, bookTitle, bookAuthor, bookPub, bookPubDate, bookContent, bookCate, bookPic FROM book WHERE bookId=?";
		
		paramList.add(bookId);
		// 存放所有查询出的图书对象
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			
			// 获取总记录数
			List<Map<String, Object>> queryResultList = jdbcUtil.findResult(sql, paramList);
			if (queryResultList != null && !queryResultList.isEmpty()){
				b = new Book(queryResultList.get(0));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("查询图书异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		return b;
	}
}
