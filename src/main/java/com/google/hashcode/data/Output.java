package com.google.hashcode.data;

import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Library;

/**
 * Output data that contains a schedule of the rides.
 */
public class Output {
    public final LibrarySet[] librarySets;

    public Output(LibrarySet[] librarySets) {
        this.librarySets = librarySets;
    }

    public static class LibrarySet {
        public Library library;
        public Book[] books;
    }
}

