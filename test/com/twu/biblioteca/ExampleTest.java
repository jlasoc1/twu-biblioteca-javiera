package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest
{


    //Test 1.1 & 1.2
    @Test
    public void testGetAnswer()
    {
        String sExpectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    //Test 1.3 & 1.4
    @Test
    public void testBookList()
    {
        String sExpectedOutput = "{Aldous Huxley : A brave new world : 1932 \nJ. R. R. Tolkien : The Lord of the Rings" +
                " : 1954 \n}";
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932 ));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954 ));
        biblioteca.setBooks(bookList);
        String dummy1 = biblioteca.getAnswer("");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
    }

}
