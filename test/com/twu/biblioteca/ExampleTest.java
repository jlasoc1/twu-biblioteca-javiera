package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest
{


    //Test 1.1 & 1.4
    @Test
    public void shouldGetWelcomeMessageWhenNoOptionIsChoosed()
    {
        String sExpectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books' \n '2. Checkout a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    //Border Case
    @Test
    public void shouldPrintWelcomeMessageWhenBibliotecaStart()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books' \n '2. Checkout a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printMessage(expectedWelcomeMessage);
        assertEquals(expectedWelcomeMessage, outContent.toString().trim());
    }

    //Test 1.2
    @Test
    public void testViewAListOfBooks()
    {
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        biblioteca.setBooks(bookList);
        String sActualOutput = biblioteca.getAnswer("1");
        String[] sParts = sActualOutput.split("\n");
        assertEquals(2, sParts.length);
    }

    // Test 1.3
    @Test
    public void testViewAListOfBooksWhenSelectOne()
    {
        String sExpectedOutput = "Aldous Huxley : A brave new world : 1932 \nJ. R. R. Tolkien : The Lord of the Rings" +
                " : 1954 \n";
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        biblioteca.setBooks(bookList);
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    // Test 1.6
    @Test
    public void testInputZeroFromUser()
    {
        String sExpectedOutput = "Thanks for using Biblioteca!";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("0");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    //Test 1.7 (Checkout Menu)
    @Test
    public void testInputTwoFromUser()
    {
        String sExpectedOutput = "Please write the name of the Book that you want to Check-Out";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
    }

    //Test 1.7 (Checkout Menu - Enter a name)
    // Check out an available book and change the state to not available
    @Test
    public void testGetAvailabilityOfABook()
    {
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        biblioteca.setBooks(bookList);
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!" ;
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("The Lord of the Rin");
        String sExpectedOutput2 = "Sorry that book is not available";
        assertEquals(sExpectedOutput2, sActualOutput2);
    }




    // Test 1.5
    @Test
    public void testInputNoValidMenuOption()
    {
        String sExpectedOutput = "Please select a valid option!\n" + "This is the menu, you can select: \n " +
                "'1. List of Books' \n '2. Checkout a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
    }



}
