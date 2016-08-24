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

import com.yzz.diary.entity.DiaryEditor;

/**
 * 
 * @author zhengsk@tcl.com
 *
 */
public class DiaryEditorDao extends BaseDao {

	//RowMapper处理类
	private class EditorRowMapper implements RowMapper<DiaryEditor>{
		public DiaryEditor mapRow(ResultSet rs, int rowNum) throws SQLException {
			DiaryEditor editor = new DiaryEditor();
			
			editor.setCode( rs.getString("code") );
			editor.setImage( rs.getString("image") );
			editor.setText( rs.getString("text") );
			editor.setFont_style( rs.getString("font_style") );
			editor.setBackground( rs.getString("background") );
			editor.setLast_sync_date( rs.getDate("last_sync_date"));
			
			return editor;
		}
	}
	
	/**
	 * 查找
	 */
	private final static String QUERY_BY_CODE =
			  " select code,text,image,font_style,background,last_sync_date "
			+ " from diary_editor "
			+ " where code=:code; ";
	
	public List<DiaryEditor> findByCode(String code){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		
		return namedParameterJdbcTemplate.query(QUERY_BY_CODE, paramMap, new EditorRowMapper());
	}
	
	/**
	 * 保存
	 */
	private final String SAVE_SQL="insert into diary_editor"
			+ " ( code,text,image,font_style,background,last_sync_date ) "
			+ " value "
			+ " ( :code,:text,:image,:font_style,:background,:last_sync_date ); ";
	public Long saveUser( DiaryEditor editor ){
		KeyHolder keyHolder=new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update( SAVE_SQL, new BeanPropertySqlParameterSource(editor), keyHolder);
		
		return keyHolder.getKey().longValue();
	}

}
