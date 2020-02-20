package com.google.hashcode.data;

import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Library;

import java.util.List;

/**
 * Input data.
 */
public class Input {
    public final int bookNum;
    public final int libraryNum;
    public final int daysNum;

    public final List<Book> books;
    public final List<Library> libraries;

    public Input(int bookNum, int libraryNum, int daysNum, List<Book> books, List<Library> libraries) {
        this.bookNum = bookNum;
        this.libraryNum = libraryNum;
        this.daysNum = daysNum;
        this.books = books;
        this.libraries = libraries;
    }
}
