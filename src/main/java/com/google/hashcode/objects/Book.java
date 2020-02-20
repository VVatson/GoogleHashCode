package com.google.hashcode.objects;

public class Book {
    public Integer id;
    public Integer score;

    public Book(Integer id, Integer score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", score=" + score + '}';
    }
}
