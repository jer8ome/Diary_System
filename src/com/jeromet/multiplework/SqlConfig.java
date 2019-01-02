package com.jeromet.multiplework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午7:10:13 
 * @version 5.0 
 * @description:  
 */


public class SqlConfig {
	private static Properties prop;
	private static File file;
	public static String DATABASE_USER;
	public static String DATABASE_PASSWORD;
	public static String DATABASE_URL;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("加载驱动程序出错！");
		}
		Properties prop = new Properties();
		File file = new File("conn.ini");
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		DATABASE_USER = prop.getProperty("user");
		DATABASE_PASSWORD = prop.getProperty("password");
		DATABASE_URL = "jdbc:mysql://"+prop.getProperty("host")
		+":"+prop.getProperty("port")+"/"+prop.getProperty("database")+"?useUnicode=true&characterEncoding=UTF-8";
	}
	
}
