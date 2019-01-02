package com.jeromet.multiplework;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午9:07:18 
 * @version 5.0 
 * @description:  
 */

public class Date {
	private String date; //日期
	
	//构造函数
	public Date(String date) {
		this.date = date;
	}
	
	//获取日期值
	public String getValue() {
		return date;
	}
	
	//输出值
	@Override
	public String toString() {
		return date;
	}
}
