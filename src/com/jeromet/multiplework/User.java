package com.jeromet.multiplework;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午7:10:13 
 * @version 5.0 
 * @description:  
 */

public class User {
	
	private String username; //用户名
	private String displayName; //显示名
	private String password; //密码
	private String email; //邮箱
	private String question; //密保问题
	private String answer; //密保问题答案
	
	//构造函数
	public User(String username,String displayName,String password,String email,String question,String answer) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
		this.email = email;
		this.question = question;
		this.answer = answer;
	}
	
	
	//获取用户名
	public String  getUsername() {
		return username;
	}
	
	//获取显示名
	public String  getDisplayName() {
		return displayName;
	}
	
	//获取密码
	public String  getPassword() {
		return password;
	}
	
	//获取邮箱
	public String  getEmail() {
		return email;
	}

	//获取密保问题
	public String  getQuestion() {
		return question;
	}
	
	//获取密保问题答案
	public String  getAnswer() {
		return answer;
	}
	
}


	




