package com.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.book.entity.User;
import com.book.util.JdbcUtil;

public class UserDao {

	/**
	 * 添加新的用户
	 * @param userData
	 * @return
	 */
	public boolean add(User userData) {
		boolean result = false;
		JdbcUtil jdbc = null;
		// 判断用户是否已存在
		if (!isExist(userData)){
			String userName = userData.getUserName();
			String userPassword = userData.getUserPassword();
			String sql = "INSERT INTO user (userName, userPassword, userAvatar) VALUES (?,?,?)";
			List<Object> params = new ArrayList<Object>();
			if (userName != null && !"".equals(userName)){
				params.add(userName);
			} else {
				return result;
			}
			if (userPassword != null && !"".equals(userPassword)){
				params.add(userPassword);
			} else {
				return result;
			}
			params.add("defaultavatar.jpg");
			try {
				jdbc = new JdbcUtil();
				jdbc.getConnection();
				if (jdbc.updateByPreparedStatement(sql, params)){
					result = true;
				}
			} catch (SQLException e) {
				throw new RuntimeException("保存用户异常！", e);
			} finally {
				if (jdbc != null) {
					jdbc.releaseConn();
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据用户名判断用户是否已存在
	 * @param user
	 * @return
	 */
	public boolean isExist(User user){
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> mapList = null;
		String username = user.getUserName();
		
		JdbcUtil jdbc = null;
		
		String sql = "SELECT * FROM user WHERE userName=?";
		if (username != null && !"".equals(username)){
			params.add(username);
		} else {
			return result;
		}
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			mapList = jdbc.findResult(sql, params);
			if (mapList != null && !mapList.isEmpty()){
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("用户查重异常！", e);
		} finally {
			if (jdbc != null){
				jdbc.releaseConn();
			}
		}
		return result;
	}

	public User login(User userData) {
		
		User result = null;
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> mapList = null;
		String username = userData.getUserName();
		String password = userData.getUserPassword();
		
		JdbcUtil jdbc = null;
		
		StringBuilder sql = new StringBuilder("SELECT userId, userName, userPassword, userAvatar FROM user WHERE 1=1");
		if (username != null && !"".equals(username)){
			params.add(username);
			sql.append(" and userName=?");
		} else {
			return result;
		}
		if (password != null && !"".equals(password)){
			params.add(password);
			sql.append(" and userPassword=?");
		} else {
			return result;
		}
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			mapList = jdbc.findResult(sql.toString(), params);
			if (mapList != null && !mapList.isEmpty()){
				result = new User(mapList.get(0));
			}
		} catch (SQLException e) {
			throw new RuntimeException("用户登录异常！", e);
		} finally {
			if (jdbc != null){
				jdbc.releaseConn();
			}
		}
		
		return result;
	}

	public boolean updatePsw(User userData) {

		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "UPDATE user SET userPassword=? WHERE userId=?";
		JdbcUtil jdbc = null;
		
		String userPassword = userData.getUserPassword();
		int userId = userData.getUserId();
		
		if (userPassword != null && !"".equals(userPassword)){
			params.add(userPassword);
		} else {
			return result;
		}
		params.add(userId);
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			if (jdbc.updateByPreparedStatement(sql, params)){
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("修改用户密码异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
	
		return result;
	}

	public boolean updateAvatar(User userData) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "UPDATE user SET userAvatar=? WHERE userId=?";
		JdbcUtil jdbc = null;
		
		String userAvatar = userData.getUserAvatar();
		int userId = userData.getUserId();
		
		if (userAvatar != null && !"".equals(userAvatar)){
			params.add(userAvatar);
		} else {
			return result;
		}
		params.add(userId);
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			if (jdbc.updateByPreparedStatement(sql, params)){
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("修改用户头像异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
	
		return result;
	}
}
