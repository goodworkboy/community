package com.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.community.model.User;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO USER (NAME,ACCOUNT_ID,TOKEN,GET_CREATE,GET_MODIFIED) VALUE (#{name},#{accountId},#{token},"
			+ "#{getCreate},#{getModified})")
	public void insert(User user);
	
}
