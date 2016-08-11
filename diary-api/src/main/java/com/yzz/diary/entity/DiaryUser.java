package com.yzz.diary.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DiaryUser implements Serializable {

	private static final long serialVersionUID = 5931171796550808655L;
	private String user_name;
	private String password;
	private String email;
	private String status;
	private String mobile_no;
	private String last_login_ip;
	private Date last_login_date;
	private String photo_uri;
	private String desc;
	private Date reg_date;
	
	public String getUser_name(){
		return user_name;
	}
	public void setUser_name( String user_name ){
		this.user_name = user_name;
	}
	
	@JsonIgnore
	public String getPassword(){
		return password;
	}
	public void setPassword( String password ){
		this.password = password;
	}
	
	public String getEmail(){
		return email==null?"":email;
	}
	public void setEmail( String email ){
		this.email = email;
	}
	
	public String getStatus(){
		return status==null?"":status;
	}
	public void setStatus( String status ){
		this.status = status;
	}
	
	public String getMobile_no(){
		return mobile_no==null?"":mobile_no;
	}
	public void setMobile_no( String mobile_no ){
		this.mobile_no = mobile_no;
	}
	
	public String getLast_login_ip(){
		return last_login_ip==null?"":last_login_ip;
	}
	public void setLast_login_ip( String last_login_ip ){
		this.last_login_ip = last_login_ip;
	}
	
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT +08:00" )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getLast_login_date(){
		return last_login_date;
	}
	public void setLast_login_date( Date last_login_date ){
		this.last_login_date = last_login_date;
	}
	public String getPhoto_uri(){
		return photo_uri;
	}
	public void setPhoto_uri( String photo_uri ){
		this.photo_uri = photo_uri;
	}
	
	public String getDesc(){
		return desc==null?"":desc;
	}
	public void setDesc( String desc ){
		this.desc = desc;
	}
	
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT +08:00" )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getReg_date(){
		return reg_date;
	}
	public void setReg_date( Date reg_date ){
		this.reg_date = reg_date;
	}

}
