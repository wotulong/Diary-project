package com.yzz.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
			user.setId( rs.getLong("id"));
			user.setUser_name( rs.getString("user_name") );
			user.setNickname( rs.getString("nickname") );
			user.setUpdate_date( rs.getDate("update_date") );
			
			return user;
		}
	}
	private final static String QUERY_BY_NAME_SQL = 
			"select id, user_name, nickname, update_date from"
			+ " user where user_name=:user_name ";
	public List<User> findByName( String name ){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_name", name );
		return namedParameterJdbcTemplate.query( QUERY_BY_NAME_SQL, paramMap, new UserRowMapper() );
	}
	
	private final static String INSERT_SQL = "insert into user(user_name,nickname,update_date) "
			+ "values(:user_name,:nickname,:update_date)";
	public void save( User user ){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_name", user.getUser_name() );
		paramMap.put("nickname", user.getNickname() );
		paramMap.put("update_date", new Date() );
		
		namedParameterJdbcTemplate.update( INSERT_SQL, paramMap );
	}

}
