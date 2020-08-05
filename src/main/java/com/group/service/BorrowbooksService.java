package com.group.service;

import com.group.pojo.Borrowbooks;

import java.util.HashMap;
import java.util.List;

public interface BorrowbooksService {
    HashMap<String, Object> selectAllBooks(Borrowbooks s);

    int returnBook(Borrowbooks borrowbooks);

    int returnAllBook(List<String> asList);

    HashMap<String, Object> selectAllNoReturnBook(Borrowbooks borrowbooks);

    int deleteBook(Borrowbooks borrowbooks);

    int deleteAllBook(List<String> asList);

    int totaldown(Integer userid);
    //批量减少total
    int totaldowns(Integer count, Integer userid);

    int totalup(Integer bookid);

    //批量增加sum
    int totalups(List<String> asList);
}
