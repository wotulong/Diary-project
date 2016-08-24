package com.yzz.diary.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author zhengsk@tcl.com
 *
 */
public class DiaryEditor extends BaseEntity {
	private static final long serialVersionUID = -4994842463635666754L;
	
	private String code;
	private String text;
	private String image;
	private String font_style;
	private String background;
	private Date last_sync_date;
	
	
	public String getCode(){
		return code==null?"":code;
	}
	public void setCode( String code ){
		this.code = code;
	}
	
	public String getText(){
		return text==null?"":text;
	}
	public void setText( String text ){
		this.text = text;
	}
	
	public String getImage(){
		return image==null?"":image;
	}
	public void setImage( String image ){
		this.image = image;
	}
	
	public String getFont_style(){
		return font_style==null?"":font_style;
	}
	public void setFont_style( String font_style ){
		this.font_style = font_style;
	}
	
	public String getBackground(){
		return background==null?"":background;
	}
	public void setBackground( String background ){
		this.background = background;
	}
	
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss", locale="GMT +08:00" )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getLast_sync_date(){
		return last_sync_date;
	}
	public void setLast_sync_date( Date last_sync_date ){
		this.last_sync_date = last_sync_date;
	}
}
