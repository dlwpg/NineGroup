package com.group.dao;

import com.group.pojo.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    int totalup(Integer bookid);

    int totalups(List<String> asList);

    List<Book> tongjizongshu();
}