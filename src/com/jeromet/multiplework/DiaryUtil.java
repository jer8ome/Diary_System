package com.jeromet.multiplework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午9:05:42 
 * @version 5.0 
 * @description:  
 */

public class DiaryUtil {
	//核对日期格式
	protected static boolean chkDateFormat(String date) {
        try {
    		String path="\\d{4}-\\d{2}-\\d{2}";//定义匹配规则 
    		Pattern p=Pattern.compile(path);//实例化Pattern 
    		Matcher m=p.matcher(date);//验证字符串内容是否合法 
    		if(!m.matches()) {//使用正则验证 	        
    		    return false;
    		}
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7)) - 1;
            int day = Integer.parseInt(date.substring(8));
            Calendar calendar = GregorianCalendar.getInstance();
            // 当 Calendar 处于 non-lenient 模式时，如果其日历字段中存在任何不一致性，它都会抛出一个异常。
            calendar.setLenient(false);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DATE, day);
           // 如果日期错误,执行该语句,必定抛出异常.
            calendar.get(Calendar.YEAR);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
	
	//检验标题是否合法
	protected static boolean chkTitle(String title) {
		if(title.length()<=12&&title.length()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//添加日记
	protected static void addDiary(Diary diary, String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String insSql = "insert into diary(writeDate,weather,mood,title,content,author) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insSql);
			pstmt.setString(1, diary.getDate().getValue());
			pstmt.setString(2, diary.getWeather());
			pstmt.setString(3, diary.getMood());
			pstmt.setString(4, diary.getTitle());
			pstmt.setString(5, diary.getContent());
			pstmt.setString(6, username);
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
	
	//删除日记
	protected static void deleteDiary(String username,int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String deleteSql = "delete from diary where id = ? and author= ?";
			pstmt = conn.prepareStatement(deleteSql);
			pstmt.setInt(1, id);
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
	
	//清空日记
	protected static void clearDiary(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String clearSql = "delete from diary where author = ?";
			pstmt = conn.prepareStatement(clearSql);
			pstmt.setString(1, username);
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
	
	//修改日记
	protected static void updateDiary(String username, int id, Diary diary) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String insSql = "update diary set writeDate = ?, weather = ?, mood = ?, title = ?, content = ? where id = ? and author = ?";
			pstmt = conn.prepareStatement(insSql);
			pstmt.setString(1, diary.getDate().getValue());
			pstmt.setString(2, diary.getWeather());
			pstmt.setString(3, diary.getMood());
			pstmt.setString(4, diary.getTitle());
			pstmt.setString(5, diary.getContent());
			pstmt.setInt(6, id);
			pstmt.setString(7, username);
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
	
	//获取当前日记id
	protected static int getID(Diary diary) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int id = -1;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select id from diary where writeDate = ? and weather = ? and mood = ? and title = ? and content = ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, diary.getDate().getValue());
			pstmt.setString(2, diary.getWeather());
			pstmt.setString(3, diary.getMood());
			pstmt.setString(4, diary.getTitle());
			pstmt.setString(5, diary.getContent());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			System.err.println("连接错误");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭异常");
			}
		}
		return id;
	}
	
	//查找日记
	protected static ArrayList<Diary> findDiary(String username, int findType, String findInfo) {
		ArrayList<Diary> diaries = new ArrayList<>();
		String findStr = null;
		switch (findType) {
		case 0:
			findStr = "and weather like ?";
			break;
		case 1:
			findStr = "and mood like ?";
			break;
		case 2:
			findStr = "and title like ?";
			break;
		case 3:
			findStr = "and content like ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select writeDate,weather,mood,title,content from diary where author = ? "+findStr+" order by writeDate";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			pstmt.setString(2, "%"+findInfo+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Diary diary = new Diary(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				diaries.add(diary);
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
		return diaries;
	}
	
	//获取当前用户所有日记
	protected static ArrayList<Diary> getAllDiary(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Diary> diaries = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select writeDate,weather,mood,title,content from diary where author = ? order by writeDate";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Diary diary = new Diary(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				diaries.add(diary);
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
		return diaries;
	}
	
	//按日期范围查找日记
	protected static ArrayList<Diary> findDiaryByRange(String username, String startTime, String endTime) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Diary> diaries = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(SqlConfig.DATABASE_URL, SqlConfig.DATABASE_USER, SqlConfig.DATABASE_PASSWORD);
			String findSql = "select writeDate,weather,mood,title,content from diary where author = ? "
					+ "and DATE_FORMAT(writeDate, '%Y-%m-%d') >= ? and DATE_FORMAT(writeDate, '%Y-%m-%d') <= ?";
			pstmt = conn.prepareStatement(findSql);
			pstmt.setString(1, username);
			pstmt.setString(2, startTime);
			pstmt.setString(3, endTime);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Diary diary = new Diary(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				diaries.add(diary);
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
		return diaries;
	}
	
}
