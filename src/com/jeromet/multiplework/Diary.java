package com.jeromet.multiplework;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午7:10:13 
 * @version 5.0 
 * @description:  
 */


public class Diary {
	private Date date; //日期
	private String weather; //天气
	private String mood; //心情
	private String title;  //标题
	private String content; //内容
	
	//构造函数
	public Diary(String date,String weather,String mood,String title,String content) {
		this.date = new Date(date);
		this.weather = weather;
		this.mood = mood;
		this.title = title;
		this.content = content;
	}

	//获取日期
	public Date getDate() {
		return date;
	}
	
	//获取天气
	public String getWeather() {
		return weather;
	}
	
	//获取心情
	public String getMood() {
		return mood;
	}
	
	//获取标题
	public String getTitle() {
		return title;
	}
	
	//获取内容
	public String getContent() {
		return content;
	}

	//输出值
	@Override
	public String toString() {
		String str = date.toString()+" "+title;
		return str;
	}
}



