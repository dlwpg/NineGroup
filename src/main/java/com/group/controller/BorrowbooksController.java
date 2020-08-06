package com.group.controller;

import com.group.pojo.Book;
import com.group.pojo.Borrowbooks;
import com.group.service.BorrowbooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("brrowBook")
public class BorrowbooksController {
    @Autowired
    BorrowbooksService bs;
    @RequestMapping("jieshu.do")
    public String jieshu(){
        return "borrowbook/brrowbooklist";
    }

    @RequestMapping("weihuan.do")
    public String weihuan(){
        return "borrowbook/noreturnbook";
    }
    //未归还
    @RequestMapping(value = "/brrowbook.ajax",produces = "application/json;charset=utf-8")
    @ResponseBody
    public HashMap<String,Object> jieshuajax(Borrowbooks borrowbooks){
        HashMap<String, Object> info=bs.selectAllBooks(borrowbooks);
        return info;
    }
//归还
    @RequestMapping(value = "noreturnbook.ajax",produces = "application/json;charset=utf-8")
    @ResponseBody
    public HashMap<String,Object> noreturnbook(Borrowbooks borrowbooks,String bookname,String author,String press){
        Book book=new Book();
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setPress(press);
//        System.err.println(book);
        borrowbooks.setBook(book);
        HashMap<String, Object> info=bs.selectAllNoReturnBook(borrowbooks);
        info.put("search",book);
        return info;
    }
//未归还
    @RequestMapping("/returnbook.ajax")
    @ResponseBody
    public int returnbook(Borrowbooks borrowbooks,Integer bookid2,Integer bookid,Integer userid){
        borrowbooks.setId(bookid);
        int info=bs.returnBook(borrowbooks);
        if (info>0){
            //user表减少1
            bs.totaldown(userid);
            //book表增加1
            bs.totalup(bookid2);
        }
        return info;
    }
    //归还
    @RequestMapping("/delete.ajax")
    @ResponseBody
    public int delete(Borrowbooks borrowbooks,Integer bookid){
        borrowbooks.setId(bookid);
        int info=bs.deleteBook(borrowbooks);
        return info;
    }
//未归还
    @RequestMapping("/returnAllBook.ajax")
    @ResponseBody
    public int returnAllBook(@RequestParam("id") String s,@RequestParam("count") Integer count,@RequestParam("userid")Integer userid,String bookids){

        int info=bs.returnAllBook(Arrays.asList(s.split(",")));
        if (info>0){
            bs.totaldowns(count,userid);
            System.err.println(bookids);
            bs.totalups(Arrays.asList(bookids.split(",")));
        }
        return info;
    }
//归还
    @RequestMapping("/deleteAllBook.ajax")
    @ResponseBody
    public int deleteAllBook(@RequestParam("id") String s){

        int info=bs.deleteAllBook(Arrays.asList(s.split(",")));
        return info;
    }
}
