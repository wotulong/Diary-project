package com.yzz.diary.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="用户名不能为空")
	private String user_name;
	@NotNull(message="昵称不能为空")
	private String nickname;
	
	public String getUser_name(){
		return user_name;
	}
	public void setUser_name( String user_name ){
		this.user_name = user_name;
	}
	
	public String getNickname(){
		return nickname;
	}
	public void setNickname( String nickname ){
		this.nickname = nickname;
	}
	
	public String toString(){
		return "user_name:" + user_name + "\tnickname:" + nickname;
	}

}
