package com.google.hashcode.objects;

import com.google.hashcode.Dispatcher;

import java.util.List;

public class LibraryProcess {
    public Integer startStep;

    public Library library;
    private Dispatcher dispatcher;
    private LibraryState state;

    public List<Book> shippedBooks;

    public List<Book> unshippedBooks;

    public LibraryProcess(Library library, Dispatcher dispatcher) {
        this.library = library;
        this.state = LibraryState.WAITING_FOR_SIGNUP;
        this.dispatcher = dispatcher;

        this.startStep =  Integer.MAX_VALUE;
    }

    public void process(int step) {
        switch(state) {

        case FINISHED:
            break;

        case WAITING_FOR_SIGNUP:
            boolean yes = dispatcher.isCurrentLibrary(this, step);
            if (yes) {
                startStep = step;
            }

            break;

        case SIGNING_UP:
            if (isRegisteredInThisStep(step)) {
                state = LibraryState.SHIPPING;
            }
            break;


        case SHIPPING:

            List<Book> booksToShip = dispatcher.getBooksToShip(this);

            shippedBooks.addAll(booksToShip);

            if (unshippedBooks.isEmpty()) {
                state = LibraryState.FINISHED;
            }
            break;

        default:
            throw new RuntimeException("UNKNOWN STATE!");
        }
    }

    public boolean isRegisteredInThisStep(int currentStep) {
        return currentStep - startStep == library.signupTime;
    }
}
