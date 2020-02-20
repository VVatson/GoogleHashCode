package com.google.hashcode;

import com.google.hashcode.comparators.BookComparator;
import com.google.hashcode.comparators.LibraryComparator;
import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Library;
import com.google.hashcode.objects.LibraryProcess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Dispatcher implementation.
 * To distribute rides to vehicles.
 */
public class DefaultDispatcher implements Dispatcher {

    private final List<Library> libraries;
    private final Set<Book> alreadyShippedBooks;
    private final Simulation simulation;
    private LibraryProcess currentLibraryProcess;
    public List<LibraryProcess> unprocessed;
    public List<LibraryProcess> processed;

    public DefaultDispatcher(List<Library> libraries, final Simulation simulation) {
        this.libraries = libraries;
        this.simulation = simulation;
        this.alreadyShippedBooks = new HashSet<>();
        this.unprocessed = new ArrayList<>(simulation.libraryProcesses);
        this.processed = new ArrayList<>();
        this.currentLibraryProcess = null;
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
        alreadyShippedBooks.addAll(booksToShip);

        return booksToShip;
    }

    @Override
    public boolean isCurrentLibrary(LibraryProcess libraryProcess, int step) {
        boolean shouldFindNewLibraryProcess = currentLibraryProcess == null ? true : currentLibraryProcess.isRegisteredInThisStep(step);

        if (shouldFindNewLibraryProcess) {
            unprocessed.sort(new LibraryComparator(step));

            currentLibraryProcess = unprocessed.remove(0);

            processed.add(currentLibraryProcess);
        }

        return libraryProcess.library.id.equals(currentLibraryProcess.library.id);
    }

}
