package com.yzz.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.yzz.diary.entity.DiaryUser;

public class DiaryUserDao extends BaseDao {

	private final String SAVE_SQL="insert into diary_user "
			+ " ( password,user_name,email,status,mobile_no,last_login_ip,cur_login_ip,last_login_date,photo_uri,desc,reg_date ) "
			+ " value "
			+ " ( :password,:user_name,:email,:status,:mobile_no,:last_login_ip,:cur_login_ip,:last_login_date,:photo_uri,:desc,:reg_date)";
	/**
	 * 保存
	 * @param user
	 * @return 保存数
	 */
	public Long saveUser( DiaryUser user ){
		KeyHolder keyHolder=new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update( SAVE_SQL, new BeanPropertySqlParameterSource(user), keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
	private final String UPDATE_SQL="update diary_user "
			+ " set email=:email,"
			+ " status=:status,mobile_no=:mobile_no,last_login_ip=:last_login_ip,"
			+ " cur_login_ip=:cur_login_ip,last_login_date=:last_login_date,"
			+ " photo_uri=:photo_uri,desc=:desc,reg_date=:reg_date"
			+ " where user_name=:user_name;";
	/**
	 * 更新除密码外字段
	 * @param user
	 * @return 更新数
	 */
	public int updateUser( DiaryUser user ){
		return this.namedParameterJdbcTemplate.update(UPDATE_SQL,  new BeanPropertySqlParameterSource(user) );
	}
	

	private final String UPDATE_PWD="update diary_user "
			+ " set password=:password"
			+ " where user_name=:user_name;";
	/**
	 * 更新密码
	 * @param pwd
	 * @param name
	 * @return 更新数
	 */
	public int updatePwd( String pwd, String name ){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put( "password", pwd );
		paramMap.put( "user_name", name );
		
		return this.namedParameterJdbcTemplate.update( UPDATE_PWD, paramMap);
	}
	

	private final static String QueryByName="select * from diary_user"
			+ "	where user_name=:user_name;";
	/**
	 * 按名字查询
	 * @param name
	 * @return 结果列表
	 */
	public List<DiaryUser> queryUser( String name ){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put( "user_name", name );
		
		return this.namedParameterJdbcTemplate.query(QueryByName, paramMap, new DairyUserRowMapper() );
	}
	
	/**
	 * RowMapper 行映射
	 * @author zhengsk@tcl.com
	 *
	 */
	private class DairyUserRowMapper implements RowMapper<DiaryUser>{
		
		/**
		 * 映射函数
		 */
		public DiaryUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			DiaryUser user = new DiaryUser();
			
			user.setCur_login_ip( rs.getString("cur_login_ip") );
			user.setDesc( rs.getString("desc") );
			user.setEmail( rs.getString("email") );
			user.setLast_login_ip( rs.getString( "last_login_ip") );
			user.setMobile_no(rs.getString( "mobile_no") );
			user.setPhoto_uri(rs.getString( "photo_uri") );
			user.setPassword(rs.getString( "password") );
			user.setStatus(rs.getString( "status") );
			user.setUser_name(rs.getString( "user_name") );
			user.setReg_date( rs.getDate("reg_date") );
			
			return user;
		}
	}
	

}
