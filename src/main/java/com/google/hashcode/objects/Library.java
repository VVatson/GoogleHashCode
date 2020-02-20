package com.google.hashcode.objects;
import java.util.List;

public class Library {
    public Integer id;
    public Integer signupTime;
    public Integer sendCount;
    public List<Book> books;

    public Library(Integer id, Integer signupTime, Integer sendCount, List<Book> books) {
        this.id = id;
        this.signupTime = signupTime;
        this.sendCount = sendCount;
        this.books = books;
    }
}
