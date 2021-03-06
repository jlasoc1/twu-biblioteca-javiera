package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesTest
  {
    public BibliotecaApp getDummyBiblioteca()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        biblioteca.setBooksContainer(new Container(bookList));
        List movieList = new ArrayList();
        movieList.add(new Movie("A Clockwork Orange", 1971, "Stanley Kubrick", 8, true, true));
        movieList.add(new Movie("Big Fish", 2003, "Tim Burton", 8, true, true));
        biblioteca.setMoviesContainer(new Container(movieList));
        List userList = new ArrayList();
        userList.add(new User("100-0001", "Sebastián Céspedes", "123abc",
                "scespedes@thoughtworks.com", movieList, "+56975647764"));
        userList.add(new User("100-0002", "Andrés Fuentes", "123abc",
                "afuentes@thoughtworks.com", movieList, "+56945621345"));
        biblioteca.setUserList(userList);
        return biblioteca;
      }


    //Test 1.1 & 1.4
    @Test
    public void shouldGetWelcomeMessageWhenIChooseMovieSection()
      {
        String sExpectedOutput = "This is the menu, you can select: \n '1. List of Movies' \n " +
                "'2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }


    //Test 1.2
    @Test
    public void testViewAListOfBooksSeparated()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("1");
        String[] sParts = sActualOutput.split("\n");
        assertEquals(2, sParts.length);
      }

    //Test 1.3
    @Test
    public void testViewAListOfBooksWhenSelectOne()
      {
        String sExpectedOutput = "A Clockwork Orange : 1971 : Stanley Kubrick : 8\n" +
                "Big Fish : 2003 : Tim Burton : 8\n";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.5
    @Test
    public void testInputNoValidMenuOption()
      {
        String sExpectedOutput = "Please select a valid option!\n" + "This is the menu, you can select: \n '1. List of Movies' \n " +
                "'2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("5");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.6
    @Test
    public void testQuiteTheAppWhenInputZeroFromUser()
      {
        String sExpectedOutput = "Thanks for using Biblioteca!";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("0");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.7
    @Test
    public void testEnterCheckOutMenuWhenInputTwoFromUser()
      {
        String sExpectedOutput = "Please write the name of the Movie that you want to Check-Out";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.8
    @Test
    public void testCheckOutAMovieCorrectly()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.9
    @Test
    public void testCheckoutAMovieWithWrongName()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & write the same book name
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("A Cloge");
        String sExpectedOutput2 = "Sorry that movie is not available";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.9.2
    @Test
    public void testCheckOutABookCorrectlyAndTriedToCheckOutItAgainFailing()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & write the same book name
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput2 = "Sorry that movie is not available";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.9.3
    @Test
    public void testViewTheListOfBooksAfterICheckOutAMovie()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & Show the new list of books
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sExpectedOutput2 = "Big Fish : 2003 : Tim Burton : 8\n";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.10
    @Test
    public void testEnterReturnMenuWhenInputThreeFromUser()
      {
        String sExpectedOutput = "Please write the name of the Movie that you want to Return";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.11
    @Test
    public void testCheckOutABookCorrectlyAnReturnIt()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the book
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput2 = "Thank you for returning the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.12
    @Test
    public void testGetABookCorrectlyAnReturnADifferentOne()
      {
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("A Clockwork Orange");
        String sExpectedOutput = "Enjoy the movie! \n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the book
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("The call of the Wild");
        String sExpectedOutput2 = "Sorry that movie does not belong to Biblioteca";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }


  }
