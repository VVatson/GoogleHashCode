package com.google.hashcode.objects;

import static com.google.hashcode.objects.VehicleState.INITIAL_STATE;

import com.google.hashcode.Dispatcher;
import com.google.hashcode.comparators.BookComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Library {
    public Integer id;
    public Integer signupTime;
    public Integer sendCount;
    public List<Book> books;
    private List<Book> sortedBooks;

    private Dispatcher dispatcher;
    private VehicleState state;

    public Library(Dispatcher dispatcher) {
        this.state = INITIAL_STATE;
        this.dispatcher = dispatcher;
        this.sortedBooks = new LinkedList<>();
        sortedBooks.addAll(books);
        sortedBooks.sort(new BookComparator());
    }

    public static void chooseBooksToShip(Library library) {
    }

    public int getValue(int rest_day_to_deadline) {
        int k = Math.min((rest_day_to_deadline - signupTime) * sendCount, books.size());
        int value = 0;
        for (int i = 0; i < k; i++) {
            value += books.get(i).score;
        }

        return value;
    }
}
