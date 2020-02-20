package com.google.hashcode;

import com.google.hashcode.comparators.BookComparator;
import com.google.hashcode.comparators.LibraryComparator;
import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Drive;
import com.google.hashcode.objects.Library;
import com.google.hashcode.objects.LibraryProcess;
import com.google.hashcode.objects.Vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Dispatcher implementation.
 * To distribute rides to vehicles.
 */
public class DefaultDispatcher implements Dispatcher {

    private final List<Library> libraries;
    private final Set<Book> alreadyShippedBooks;
    private final Simulation simulation;

    public DefaultDispatcher(List<Library> libraries, final Simulation simulation) {
        this.libraries = libraries;
        this.simulation = simulation;
        this.alreadyShippedBooks = new HashSet<>();
    }

    @Override
    public List<Book> getBooksToShip(LibraryProcess libraryProcess) {
        List<Book> booksToShip = new ArrayList<>();

        libraryProcess.unshippedBooks.removeAll(alreadyShippedBooks);

        libraryProcess.unshippedBooks.sort(new BookComparator());

        Iterator<Book> iter = libraryProcess.unshippedBooks.listIterator();

        for (int i = 0; i < libraryProcess.library.sendCount; i++) {
            if (iter.hasNext()) {
                Book book = iter.next();
                booksToShip.add(book);
                iter.remove();
            }
        }

        return booksToShip;
    }

}
