package com.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.book.entity.Book;
import com.book.util.JdbcUtil;

public class RecommendDao {

	/**
	 * 根据书籍ID推荐书籍
	 * @param bookData
	 * @return
	 */
	public boolean add(Book bookData) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "INSERT INTO recommend(bookId) VALUES(?)";
		JdbcUtil jdbc = null;
		
		int bookId = bookData.getBookId();
		
		params.add(bookId);
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			if (jdbc.updateByPreparedStatement(sql, params)){
				// 如果保存成功则返回结果为true
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("推荐书籍异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
		return result;
	}

	public List<Book> findAll(){
		List<Object> paramList = new ArrayList<Object>();
		
		String sql = "SELECT book.bookId, bookTitle, bookAuthor, bookPub, bookPubDate, bookContent, bookCate, bookPic FROM recommend, book WHERE recommend.bookId=book.bookId";
		
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
}
