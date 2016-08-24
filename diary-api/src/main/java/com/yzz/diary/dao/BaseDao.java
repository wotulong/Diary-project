package com.yzz.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseDao {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//获取字符串
	protected String getStrVal( ResultSet rs, String param ){
		try {
			return rs.getString( param );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//获取时间
	protected Date getDateVal( ResultSet rs, String param ){
		try {
			return rs.getDate( param );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
