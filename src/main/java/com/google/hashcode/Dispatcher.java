package com.google.hashcode;

import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.LibraryProcess;

import java.util.List;

/**
 * Dispatcher to find the optimal ride for a vehicle.
 */
public interface Dispatcher {

    List<Book> getBooksToShip(LibraryProcess libraryProcess);

    boolean isCurrentLibrary(LibraryProcess libraryProcess, int step);
}
