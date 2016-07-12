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
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected Date update_date;
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	public Long getId(){
		return id;
	}
	public void setId( Long id ){
		this.id = id;
	}
	
	@JsonIgnore
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT 08:00")
	@Temporal( TemporalType.TIMESTAMP )
	@Column(name="update_date")
	public Date getUpdate_date(){
		return update_date;
	}
	public void setUpdate_date( Date update_date ){
		this.update_date = update_date;
	}

}
