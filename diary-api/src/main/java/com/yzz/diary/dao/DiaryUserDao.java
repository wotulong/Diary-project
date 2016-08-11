package com.yzz.diary.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.yzz.diary.entity.DiaryUser;

public class DiaryUserDao extends BaseDao {

	private final String SAVE_SQL="insert into diary_user"
			+ " ( password,user_name,email,status,mobile_no,last_login_ip,last_login_date,photo_uri,desc,reg_date ) "
			+ " value "
			+ " ( :password,:user_name,:email,:status,:mobile_no,:last_login_ip,:last_login_date,:photo_uri,:desc,:reg_date)";
	public Long saveUser( DiaryUser user ){
		KeyHolder keyHolder=new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update( SAVE_SQL, new BeanPropertySqlParameterSource(user), keyHolder);
		
		return keyHolder.getKey().longValue();
	}

}
