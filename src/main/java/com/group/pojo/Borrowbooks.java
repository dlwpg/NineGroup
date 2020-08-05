package com.group.pojo;

import java.util.Date;

public class Borrowbooks extends MyPage {
    private Integer id;

    private Integer userid;

    private Integer bookid;

    private String  date;

    private Integer state;

    private String appraisal;

    private Book book;
    public Borrowbooks(){}

    public Borrowbooks(Integer id, Integer userid, Integer bookid, String date, Integer state, String appraisal, Book book) {
        this.id = id;
        this.userid = userid;
        this.bookid = bookid;
        this.date = date;
        this.state = state;
        this.appraisal = appraisal;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }

    @Override
    public String toString() {
        return "Borrowbooks{" +
                "id=" + id +
                ", userid=" + userid +
                ", bookid=" + bookid +
                ", date=" + date +
                ", state=" + state +
                ", appraisal='" + appraisal + '\'' +
                ", book=" + book +
                '}';
    }
}