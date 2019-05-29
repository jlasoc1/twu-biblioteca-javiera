package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest
{


    //Test 1.1 & 1.2
    @Test
    public void shouldGetWelcomeMessageWhenNoOptionIsChoosed()
    {
        String sExpectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    @Test
    public void shouldPrintWelcomeMessageWhenBibliotecaStart()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printMessage(expectedWelcomeMessage);
        assertEquals(expectedWelcomeMessage, outContent.toString().trim());
    }

    //Test 1.3 & 1.4
    @Test
    public void testBookList()
    {
        String sExpectedOutput = "Aldous Huxley : A brave new world : 1932 \nJ. R. R. Tolkien : The Lord of the Rings" +
                " : 1954 \n";
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932 ));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954 ));
        biblioteca.setBooks(bookList);
        String sEmpthyOutput = biblioteca.getAnswer("");
        String sActualOutput = biblioteca.getAnswer("1");
        String sErrorOuput = biblioteca.getAnswer("2");
        String sQuitApp = biblioteca.getAnswer("0");
        assertEquals(sExpectedOutput, sActualOutput);
    }

}
