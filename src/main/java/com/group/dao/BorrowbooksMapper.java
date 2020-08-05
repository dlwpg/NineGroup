package com.group.dao;

import com.group.pojo.Borrowbooks;

import java.util.List;

public interface BorrowbooksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Borrowbooks record);

    int insertSelective(Borrowbooks record);

    Borrowbooks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Borrowbooks record);

    int updateByPrimaryKey(Borrowbooks record);

    //分页查询未归还
    List<Borrowbooks> selectByPage(int userid);

    //批量还书
    int returnAllBook(List<String> asList);

    //分页查询以还书
    List<Borrowbooks> selectAllNoReturnBookByPage(Borrowbooks borrowbooks);

    int updateById(Integer id);
    //批量删除（假删除）
    int deleteAllBook(List<String> asList);

}