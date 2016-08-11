package com.yzz.diary.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.yzz.diary.entity.DiaryUserLog;

public class DiaryUserLogDao extends BaseDao {

	private final String SAVE_SQL="insert into diary_user_log"
			+ " ( user_name,mac,ip,action,date ) "
			+ " value "
			+ " ( :user_name,:mac,:ip,:action,:date )";
	public Long saveUserLog( DiaryUserLog log ){
		KeyHolder keyHolder=new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update( SAVE_SQL, new BeanPropertySqlParameterSource(log), keyHolder);
		
		return keyHolder.getKey().longValue();
	}
}
