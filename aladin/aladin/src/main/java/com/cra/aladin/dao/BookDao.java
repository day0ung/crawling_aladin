package com.cra.aladin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cra.aladin.dto.BookDto;

@Mapper
@Repository
public interface BookDao {
	
	public void insertBookData(BookDto dto);

}
