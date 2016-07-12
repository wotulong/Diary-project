package com.yzz.diary.entity;

import java.io.Serializable;

public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user_name;
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
