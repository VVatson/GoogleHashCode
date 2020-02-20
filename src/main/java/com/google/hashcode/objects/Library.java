package com.google.hashcode.objects;

import static com.google.hashcode.objects.VehicleState.INITIAL_STATE;

import com.google.hashcode.Dispatcher;

import java.util.List;

public class Library {
    public Integer id;
    public Integer signupTime;
    public List<Book> books;

    private Dispatcher dispatcher;
    private VehicleState state;

    public Library(Dispatcher dispatcher) {
        this.state = INITIAL_STATE;
        this.dispatcher = dispatcher;
    }

    public static void chooseBooksToShip(Library library) {
    }
}
