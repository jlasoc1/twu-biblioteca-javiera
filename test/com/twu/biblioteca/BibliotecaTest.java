package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest
  {
    public Container getDummyContainer(){
      List bookList = new ArrayList();
      bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
      bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
      return new Container(bookList);
    }


    @Test
    public void shouldGetWelcomeMessageWhenNoOptionIsChoosed()
      {
        String sExpectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.1 & 1.4
    @Test
    public void shouldGetWelcomeMessageWhenIChooseBookSection()
      {
        String sExpectedOutput = "This is the menu, you can select: \n '1. List of Books' \n " +
                "'2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput2 = biblioteca.getAnswer("");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }


    //Border Case
    @Test
    public void shouldPrintWelcomeMessageWhenBibliotecaStart()
      {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Books' \n '2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printMessage(expectedWelcomeMessage);
        assertEquals(expectedWelcomeMessage, outContent.toString().trim());
      }

    //Test 1.2
    @Test
    public void testViewAListOfBooksSeparated()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        String sActualOutput3 = biblioteca.getAnswer("1");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("1");
        String[] sParts = sActualOutput.split("\n");
        assertEquals(2, sParts.length);
      }

    //Test 1.3
    @Test
    public void testViewAListOfBooksWhenSelectOne()
      {
        String sExpectedOutput = "Aldous Huxley : A brave new world : 1932 \nJ. R. R. Tolkien : The Lord of the Rings" +
                " : 1954 \n";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        String sActualOutput3 = biblioteca.getAnswer("1");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.5
    @Test
    public void testInputNoValidMenuOption()
      {
        String sExpectedOutput = "Please select a valid option!\n" + "This is the menu, you can select: " +
                "\n '1. List of Books' \n '2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput3 = biblioteca.getAnswer("1");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("5");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.6
    @Test
    public void testQuiteTheAppWhenInputZeroFromUser()
      {
        String sExpectedOutput = "Thanks for using Biblioteca!";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("0");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.7
    @Test
    public void testEnterCheckOutMenuWhenInputTwoFromUser()
      {
        String sExpectedOutput = "Please write the name of the Book that you want to Check-Out";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.8
    @Test
    public void testCheckOutABookCorrectly()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!";
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.9
    @Test
    public void testCheckoutABookWithWrongName()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord");
        String sExpectedOutput = "Sorry that book is not available";
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.9.2
    @Test
    public void testCheckOutABookCorrectlyAndTriedToCheckOutItAgainFailing()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & write the same book name
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput2 = "Sorry that book is not available";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.9.3
    @Test
    public void testViewTheListOfBooksAfterICheckOutABook()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & Show the new list of books
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("1");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sExpectedOutput2 = "Aldous Huxley : A brave new world : 1932 \n";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.10
    @Test
    public void testEnterReturnMenuWhenInputThreeFromUser()
      {
        String sExpectedOutput = "Please write the name of the Book that you want to Return";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.11
    @Test
    public void testCheckOutABookCorrectlyAnReturnIt()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the book
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput2 = "Thank you for returning the book";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.12
    @Test
    public void testGetABookCorrectlyAnReturnADifferentOne()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooksContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the book!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the book
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("The call of the Wild");
        String sExpectedOutput2 = "Sorry that book does not belong to Biblioteca";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }


  }
