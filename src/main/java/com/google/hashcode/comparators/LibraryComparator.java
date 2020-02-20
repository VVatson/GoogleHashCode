package com.google.hashcode.comparators;

import com.google.hashcode.objects.LibraryProcess;

import java.util.Comparator;

public class LibraryComparator implements Comparator<LibraryProcess> {

    private final int day_to_deadline;

    public LibraryComparator(int day_to_deadline) {
        this.day_to_deadline = day_to_deadline;
    }

    @Override
    public int compare(LibraryProcess o1, LibraryProcess o2) {
        return o1.library.getValue(day_to_deadline) - o2.library.getValue(day_to_deadline);
    }
}
