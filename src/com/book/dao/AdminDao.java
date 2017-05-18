package com.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.book.entity.Admin;
import com.book.util.JdbcUtil;

public class AdminDao {

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) {
		Admin result = null;
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> mapList = null;
		String username = admin.getAdminName();
		String password = admin.getAdminPassword();
		
		JdbcUtil jdbc = null;
		
		String sql = "SELECT adminId, adminName, adminPassword FROM admin WHERE adminName=? AND adminPassword=?";
		if (username != null && !"".equals(username)){
			params.add(username);
		} else {
			return result;
		}
		if (password != null && !"".equals(password)){
			params.add(password);
		} else {
			return result;
		}
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			mapList = jdbc.findResult(sql, params);
			if (mapList != null && !mapList.isEmpty()){
				Map<String, Object> map = mapList.get(0);
				result = new Admin();
				result.setAdminId((int) map.get("adminId"));
				result.setAdminName((String) map.get("adminName"));
				result.setAdminPassword((String) map.get("adminPassword"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("管理员登录异常！", e);
		} finally {
			if (jdbc != null){
				jdbc.releaseConn();
			}
		}
		return result;
	}

	/**
	 * 添加新的管理员
	 * @param adminData
	 * @return
	 */
	public boolean add(Admin adminData) {
		boolean result = false;
		JdbcUtil jdbc = null;
		// 判断用户是否已存在
		if (!isExist(adminData)){
			String adminName = adminData.getAdminName();
			String adminPassword = adminData.getAdminPassword();
			String sql = "INSERT INTO admin (adminName, adminPassword) VALUES (?,?)";
			List<Object> params = new ArrayList<Object>();
			if (adminName != null && !"".equals(adminName)){
				params.add(adminName);
			} else {
				return result;
			}
			if (adminPassword != null && !"".equals(adminPassword)){
				params.add(adminPassword);
			} else {
				return result;
			}
			try {
				jdbc = new JdbcUtil();
				jdbc.getConnection();
				if (jdbc.updateByPreparedStatement(sql, params)){
					result = true;
				}
			} catch (SQLException e) {
				throw new RuntimeException("保存管理员异常！", e);
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
	 * @param admin
	 * @return
	 */
	public boolean isExist(Admin admin){
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> mapList = null;
		String username = admin.getAdminName();
		
		JdbcUtil jdbc = null;
		
		String sql = "SELECT * FROM admin WHERE adminName=?";
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
			throw new RuntimeException("管理员登录异常！", e);
		} finally {
			if (jdbc != null){
				jdbc.releaseConn();
			}
		}
		return result;
	}

	public boolean update(Admin adminData) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "UPDATE admin SET adminPassword=? WHERE adminId=?";
		JdbcUtil jdbc = null;
		
		String adminPassword = adminData.getAdminPassword();
		int adminId = adminData.getAdminId();
		
		if (adminPassword != null && !"".equals(adminPassword)){
			params.add(adminPassword);
		} else {
			return result;
		}
		params.add(adminId);
		
		try {
			jdbc = new JdbcUtil();
			jdbc.getConnection();
			if (jdbc.updateByPreparedStatement(sql, params)){
				result = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("修改管理员密码异常！", e);
		} finally {
			if (jdbc != null) {
				jdbc.releaseConn();
			}
		}
	
		return result;
	}
}
