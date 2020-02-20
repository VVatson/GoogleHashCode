package com.google.hashcode.data;

import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Library;
import com.google.hashcode.objects.LibraryProcess;

import java.util.List;

/**
 * Output data that contains a schedule of the rides.
 */
public class Output {
    public final LibrarySet[] librarySets;

    public Output(List<LibraryProcess> processed) {
        this.librarySets = processed.stream()
                .map(libProc -> new LibrarySet(libProc.library, libProc.shippedBooks.toArray(new Book[0])))
                .toArray(LibrarySet[]::new);
    }

    public static class LibrarySet {
        public Library library;
        public Book[] books;

        public LibrarySet(Library library, Book[] books) {
            this.library = library;
            this.books = books;
        }
    }
}

