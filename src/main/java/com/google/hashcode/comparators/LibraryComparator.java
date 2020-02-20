package com.google.hashcode.comparators;

import com.google.hashcode.objects.Library;

import java.util.Comparator;

public class LibraryComparator implements Comparator<Library> {

    private final int day_to_deadline;

    public LibraryComparator(int day_to_deadline) {
        this.day_to_deadline = day_to_deadline;
    }

    @Override
    public int compare(Library o1, Library o2) {
        return o1.getValue(day_to_deadline) - o2.getValue(day_to_deadline);
    }
}
