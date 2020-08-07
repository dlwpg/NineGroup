package com.group.service.impl;

import com.group.dao.BookMapper;
import com.group.pojo.Book;
import com.group.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
@Autowired
private BookMapper bookMapper;

    @Override
    public String  sss() {
        Double d=4.2e12;
        return "";

    }

    @Override
    public List<Book> tongjizongshu() {
        return bookMapper.tongjizongshu();
    }
}
