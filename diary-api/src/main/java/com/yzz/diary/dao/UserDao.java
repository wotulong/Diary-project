package com.yzz.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.yzz.diary.entity.User;

@Component
public class UserDao extends BaseDao {
	
	private class UserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUser_name( rs.getString("user_name") );
			user.setNickname( rs.getString("nickname") );
			return user;
		}
	}
	private final static String QUERY_BY_NAME = 
			"select user_name, nickname from"
			+ " user where user_name=:user_name ";
	public List<User> findByName( String name ){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_name", name );
		return this.namedParameterJdbcTemplate.query( QUERY_BY_NAME, paramMap, new UserRowMapper() );
	}

}
