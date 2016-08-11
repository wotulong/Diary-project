package com.yzz.diary.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DiaryUserLog implements Serializable {

	private static final long serialVersionUID = 4743773922014369523L;
	
	private Long id;
	private String user_name;
	private String mac;
	private String ip;
	private String action;
	private Date date;
	
	@Id
	@Column(name="id")
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	public Long getId(){
		return id;
	}
	public void setId( Long id ){
		this.id = id;
	}
	
	public String getUser_name(){
		return user_name;
	}
	public void setUser_name( String user_name ){
		this.user_name = user_name;
	}
	
	public String getMac(){
		return mac==null?"":mac;
	}
	public void setMac( String mac ){
		this.mac = mac;
	}
	
	public String getIp(){
		return ip==null?"":ip;
	}
	public void setIp( String ip ){
		this.ip = ip;
	}
	
	public String getAction(){
		return action==null?"":action;
	}
	public void setAction( String action ){
		this.action = action;
	}
	
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT +08:00" )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getDate(){
		return date;
	}
	public void setDate( Date date ){
		this.date = date;
	}
	
}
