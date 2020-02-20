package com.google.hashcode.objects;

import static com.google.hashcode.objects.VehicleState.GO_TO_DESTINATION;

import com.google.hashcode.Dispatcher;
import com.google.hashcode.Distance;

import java.util.List;

public class LibraryProcess {
    public Integer remainingTimeToBeSignedUp;

    public Library library;
    private Dispatcher dispatcher;
    private LibraryState state;

    private List<Book> shippedBooks;

    public List<Book> unshippedBooks;

    public LibraryProcess(Library library, Dispatcher dispatcher) {
        this.library = library;
        this.state = LibraryState.WAITING_FOR_SIGNUP;
        this.dispatcher = dispatcher;

        this.remainingTimeToBeSignedUp = library.signupTime;
    }

    public void process(int step) {
        switch(state) {

        case FINISHED:
            break;

        case WAITING_FOR_SIGNUP:

            break;

        case SIGNING_UP:
            remainingTimeToBeSignedUp--;
            if (remainingTimeToBeSignedUp == 0) {
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
}
