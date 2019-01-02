package com.jeromet.multiplework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午7:10:13 
 * @version 5.0 
 * @description:  
 */

public class UserUtil {
	
	
	//密保问题
	protected final static String[] questions = {"你的生日在几月？",
			"你叫什么名字？",
			"你最喜欢的国家：",
			"你最喜欢的颜色：",
			"世界杯最喜欢哪个队？"};
	
	//验证用户名
	protected static boolean verifyUsername(String username) {
		//用户名是不是只包含字符，首字母是否为大写
		if(username.matches("[\\w+]{5,20}") && Character.isLetter(username.charAt(0))) { 
			return true;
		}
		return false;
	}

	//验证显示名
	protected static boolean verifyDisplayName(String displayName) {
		if(displayName.length()>2&&displayName.length()<21) {
			return true;
		}else {
			return false;
		}
	}
	
	//验证密码
	protected static boolean verifyPassword(String password) {
		if(password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[-.!@#$%^&*()+?><]).{8,30}$")) {
			return true;
		}
		return false;
	}

	//确认密码
	protected static boolean verifyPasswordAgain(String password, String passwordAgain) {
		if(passwordAgain.equals(password)) {
			return true;
		}else {
			return false;	
		}
	}
	
	//验证邮箱
	protected static boolean verifyEmail(String email) {
		if(email.matches("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{0,50}")) {
			return true;
		}else {
			return false;
		}

	}		
	
	//验证密码提示
	protected static boolean verifyTips(String answer) {
		if(answer.length()>0 && answer.length()<20){
			return true;
		}
		return false;
	}
	
	//验证密码是否正确
	protected static boolean verifyPasswordOwned(String username, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select password from user where username = ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(password)) {
					return true;
				}else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
		return false;
	}
	
	
	
	//验证答案是否正确
	protected static boolean verifyQuestionOwned(String username, String question, String answer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select answer from user where username = ? and question = ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			pstmt.setString(2, question);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(answer)) {
					return true;
				}else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
		return false;
	}
	
	//验证用户是否存在
	protected static boolean isExistUser(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select username from user where username = ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
		return true;
	}
	
	//获取显示名
	protected static String getDisplayName(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String displayName = null;
		try {
			//建立连接
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, 
					SqlConfig.DATABASE_PASSWORD);
			//构建并执行sql语句
			String findSql = "select displayname from user "
					+ "where username = ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			//接收结果
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				displayName = rs.getString(1);
			}
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
		return displayName;
	}
	
	//添加用户到数据库
	protected static void addUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String insSql = "insert user(username,displayname,password,email,question,answer) values"
					+ "(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insSql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getDisplayName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getQuestion());
			pstmt.setString(6, user.getAnswer());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
	}
	
	//修改密码
	protected static void updatePassword(String username, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String updateSql = "update user set password = ? where username = ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
	}
	
	
	
	//切换登录状态
	protected static void loginControl(String username, boolean isLogin) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String updateSql = "update user set isLogin = ? where username = ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setBoolean(1, isLogin);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
	}
	
}
