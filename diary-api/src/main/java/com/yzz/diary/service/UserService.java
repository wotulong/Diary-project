package com.yzz.diary.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzz.diary.dao.UserDao;
import com.yzz.diary.entity.User;

@Service
@Transactional("jdbcTxManager")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public List<User> findUserByName( String name ){
		logger.info( "find user by name:" + name );
		List<User> users = userDao.findByName(name);
		return users;
	}
}
