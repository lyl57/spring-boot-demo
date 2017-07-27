package com.example.apibean;

/**
 * Created by lyl57 on 2017/3/25.
 */
//定义联表查询返回实体类
public class UserBook {
    private Long id;

    private String userName;
    private String bookName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
