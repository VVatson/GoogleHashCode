package com.google.hashcode.comparators;

import com.google.hashcode.objects.Book;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.score -o2.score;
    }
}
