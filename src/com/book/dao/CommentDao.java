package com.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.book.entity.Comment;
import com.book.entity.User;
import com.book.util.JdbcUtil;

public class CommentDao {

	public boolean save(Comment comment){
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "INSERT INTO comment(bookId, userId, commentText, commentTitle) VALUES(?,?,?,?)";
		JdbcUtil jdbc = null;
		
		
		int bookId = comment.getBookId();
		int userId = comment.getUserId();
		String commentText = comment.getCommentText();
		String commentTitle = comment.getCommentTitle();
		
		params.add(bookId);
		params.add(userId);
		if (commentText != null && !"".equals(commentText)){
			params.add(commentText);
		} else {
			return result;
		}
		if (commentTitle != null && !"".equals(commentTitle)){
			params.add(commentTitle);
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
			throw new RuntimeException("保存评论异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
		return result;
	}

	public List<Comment> findByBook(int bookId) {
		List<Comment> result = new ArrayList<Comment>();
		String sqlComment = "SELECT `comment`.commentId, `comment`.commentText, `comment`.commentTitle, `comment`.userId FROM `comment`, book WHERE book.bookId=`comment`.bookId AND book.bookId=?";
		String sqlUser = "SELECT `user`.userName, `user`.userPassword, `user`.userAvatar FROM `user` WHERE userId=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bookId);
		JdbcUtil jdbcUtil = null;
		System.out.println("start!");
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			
			// 获取总记录数
			List<Map<String, Object>> queryResultList = jdbcUtil.findResult(sqlComment, paramList);
			if (queryResultList != null && !queryResultList.isEmpty()){
				for (Map<String, Object> map : queryResultList) {
					
					Comment comment = new Comment();
					
					int commentId = (int) map.get("commentId");
					String commentText = (String) map.get("commentText");
					String commentTitle = (String) map.get("commentTitle");
					int userId = (int) map.get("userId");
					
					List<Object> params = new ArrayList<Object>();
					params.add(userId);
					
					// 获取User对象
					User user = new User();
					List<Map<String, Object>> queryUser = jdbcUtil.findResult(sqlUser, params);
					if (queryUser != null && !queryUser.isEmpty()){
						Map<String, Object> mapUser = queryUser.get(0);
						String userName = (String) mapUser.get("userName");
						String userPassword = (String) mapUser.get("userPassword");
						String userAvatar = (String) mapUser.get("userAvatar");
						user.setUserId(userId);
						user.setUserName(userName);
						user.setUserPassword(userPassword);
						user.setUserAvatar(userAvatar);
						System.out.println("user : " + user.getUserName());
					}
					
					comment.setCommentId(commentId);
					comment.setBookId(bookId);
					comment.setUserId(userId);
					comment.setCommentTitle(commentTitle);
					comment.setCommentText(commentText);
					
					comment.setUser(user);
					
					result.add(comment);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("查询图书所有评论异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		return result;
	}
}
