package com.google.hashcode;

import com.google.hashcode.data.Input;
import com.google.hashcode.data.Output;
import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileHandler is designed to interact with files.
 */
public abstract class FileHandler {
    public static final String INPUT_DIR = "src/main/resources/input/";
    public static final String OUTPUT_DIR = "src/main/resources/output/";

    public static Input read(final String fileName) {
        BufferedReader br = null;
        FileReader fr = null;

        final int bookNum;
        final int libraryNum;
        final int daysNum;
        final List<Book> books = new ArrayList<>();
        final List<Library> libraries = new ArrayList<>();

        try {
            fr = new FileReader(INPUT_DIR + fileName);
            br = new BufferedReader(fr);

            // first line
            String curLine = br.readLine().trim();
            String[] split = curLine.split(" ");
            bookNum = Integer.parseInt(split[0]);
            libraryNum = Integer.parseInt(split[1]);
            daysNum = Integer.parseInt(split[2]);

            // second line - books
            curLine = br.readLine().trim();
            split = curLine.split(" ");
            for (int i = 0; i < split.length; ++i) {
                books.add(new Book(i, Integer.parseInt(split[i])));
            }

            for (int libCounter = 0; libCounter < libraryNum; ++libCounter) {
                // common library info
                curLine = br.readLine();
                split = curLine.split(" ");
                final int booksPerLib = Integer.parseInt(split[0]);
                final int signupTime = Integer.parseInt(split[1]);
                final int sendCount = Integer.parseInt(split[2]);

                // books
                final List<Book> libBooks = new ArrayList<>();
                curLine = br.readLine();
                split = curLine.split(" ");
                Arrays.stream(split).forEach(s -> {
                    int bookId = Integer.parseInt(s);
                    libBooks.add(books.get(bookId));
                });

                libraries.add(new Library(libCounter, signupTime, sendCount, libBooks));
            }

            return new Input(bookNum, libraryNum, daysNum, books, libraries);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void write(final String fileName, final Output output) {
        Output.LibrarySet[] sets = output.librarySets;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(OUTPUT_DIR + fileName, false);
            bw = new BufferedWriter(fw);

            bw.write(Integer.toString(sets.length));
            bw.newLine();

            for (Output.LibrarySet set : sets) {
                bw.write(Integer.toString(set.library.id) + ' ' + Integer.toString(set.books.length));
                bw.newLine();
                for (int i = 0; i < set.books.length; ++i) {
                    bw.write(Integer.toString(set.books[i].id));
                    if (i < set.books.length - 1) {
                        bw.write(' ');
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}