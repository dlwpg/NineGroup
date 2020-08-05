package com.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.dao.BookMapper;
import com.group.dao.BorrowbooksMapper;
import com.group.dao.UserMapper;
import com.group.pojo.Borrowbooks;
import com.group.pojo.User;
import com.group.service.BorrowbooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BorrowbooksServiceImpl implements BorrowbooksService {
    @Autowired
    BorrowbooksMapper bbm;
    @Autowired
    UserMapper um;
    @Autowired
    BookMapper bm;
    @Override
    public HashMap<String, Object> selectAllBooks(Borrowbooks s) {
        PageHelper.startPage(s.getPage(),s.getRow());
        //调用sql
        List<Borrowbooks> borrowbooksList=bbm.selectByPage(s.getUserid());
        for (Borrowbooks b:borrowbooksList
             ) {
            System.out.println(b);
        }

        PageInfo<Borrowbooks> pageInfo=new PageInfo<Borrowbooks>(borrowbooksList);

        //构建数据类型
        HashMap<String,Object> map=new HashMap<String,Object>();
        //获取每页类容
        map.put("list",pageInfo.getList());
        //获取总条数
        map.put("count",pageInfo.getTotal());
        //上一页
        map.put("prepage",pageInfo.getPrePage());
        //下一页
        map.put("nextpage",pageInfo.getNextPage());
        //总页数页
        map.put("pages",pageInfo.getPages());
        //首页
        map.put("firstpage",pageInfo.getNavigateFirstPage());
        //尾页
        map.put("endpage",pageInfo.getNavigateLastPage());
        //当前页
        map.put("thispage",s.getPage());

        return map;
    }

    @Override
    public int returnBook(Borrowbooks borrowbooks) {
        return bbm.updateByPrimaryKeySelective(borrowbooks);
    }

    @Override
    public int returnAllBook(List<String> asList) {
        return bbm.returnAllBook(asList);
    }

    @Override
    public HashMap<String, Object> selectAllNoReturnBook(Borrowbooks borrowbooks) {
        PageHelper.startPage(borrowbooks.getPage(),borrowbooks.getRow());
        //调用sql
        List<Borrowbooks> borrowbooksList=bbm.selectAllNoReturnBookByPage(borrowbooks);
        for (Borrowbooks b:borrowbooksList
        ) {
            System.out.println(b);
        }

        PageInfo<Borrowbooks> pageInfo=new PageInfo<Borrowbooks>(borrowbooksList);

        //构建数据类型
        HashMap<String,Object> map=new HashMap<String,Object>();
        //获取每页类容
        map.put("list",pageInfo.getList());
        //获取总条数
        map.put("count",pageInfo.getTotal());
        //上一页
        map.put("prepage",pageInfo.getPrePage());
        //下一页
        map.put("nextpage",pageInfo.getNextPage());
        //总页数页
        map.put("pages",pageInfo.getPages());
        //首页
        map.put("firstpage",pageInfo.getNavigateFirstPage());
        //尾页
        map.put("endpage",pageInfo.getNavigateLastPage());
        //当前页
        map.put("thispage",borrowbooks.getPage());

        return map;
    }

    @Override
    public int deleteBook(Borrowbooks borrowbooks) {
        return bbm.updateById(borrowbooks.getId());
    }

    @Override
    public int deleteAllBook(List<String> asList) {
        return bbm.deleteAllBook(asList);
    }

    @Override
    public int totaldown(Integer userid) {
        return um.totaldown(userid);
    }

    @Override
    public int totaldowns(Integer count, Integer userid) {
        return um.totaldowns(count,userid);
    }

    @Override
    public int totalup(Integer bookid) {
        return bm.totalup(bookid);
    }

    @Override
    public int totalups(List<String> asList) {
        return bm.totalups(asList);
    }
}
