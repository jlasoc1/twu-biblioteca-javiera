package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesTest
  {
    public Container getDummyContainer(){
      List movieList = new ArrayList();
      movieList.add(new Movie("A Clockwork Orange", 1971, "Stanley Kubrick", 8, true, true));
      movieList.add(new Movie("Big Fish", 2003, "Tim Burton", 8, true, true));
      return new Container(movieList);
    }

    @Test
    public void shouldGetWelcomeMessageWhenNoOptionIsChoosed()
      {
        String sExpectedOutput = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "This is the menu, you can select: \n '1. List of Movies' \n '2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.2
    @Test
    public void testViewAListOfMoviesSeparated()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        String sActualOutput = biblioteca.getAnswer("1");
        String[] sParts = sActualOutput.split("\n");
        assertEquals(2, sParts.length);
      }

    //Test 1.3
    @Test
    public void testViewAListOfMoviesWhenSelectOne()
      {
        String sExpectedOutput = "A Clockwork Orange : 1971 : Stanley Kubrick : 8 \n" + "Big Fish : 2003 : Tim Burton : 8";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.5
    @Test
    public void testInputNoValidMenuOption()
      {
        String sExpectedOutput = "Please select a valid option!\n" + "This is the menu, you can select: " +
                "\n '1. List of Movies' \n '2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("5");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.6
    @Test
    public void testQuiteTheAppWhenInputZeroFromUser()
      {
        String sExpectedOutput = "Thanks for using Biblioteca!";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("0");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.7
    @Test
    public void testEnterCheckOutMenuWhenInputTwoFromUser()
      {
        String sExpectedOutput = "Please write the name of the Movie that you want to Check-Out";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.8
    @Test
    public void testCheckOutAMovieCorrectly()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the movie!";
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.9
    @Test
    public void testCheckoutAMovieWithWrongName()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord");
        String sExpectedOutput = "Sorry that movie is not available";
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.9.2
    @Test
    public void testCheckOutAMovieCorrectlyAndTriedToCheckOutItAgainFailing()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the movie!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & write the same movie name
        biblioteca.getAnswer("2");
        String sActualOutput2 = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput2 = "Sorry that movie is not available";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.9.3
    @Test
    public void testViewTheListOfMoviesAfterICheckOutAMovie()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the movie!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & Show the new list of movies
        biblioteca.getAnswer("1");
        String sActualOutput2 = biblioteca.getAnswer("1");
        String sExpectedOutput2 = "Aldous Huxley : A brave new world : 1932 \n";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.10
    @Test
    public void testEnterReturnMenuWhenInputThreeFromUser()
      {
        String sExpectedOutput = "Please write the name of the Movie that you want to Return";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    //Test 1.11
    @Test
    public void testCheckOutAMovieCorrectlyAnReturnIt()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the movie!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the movie
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput2 = "Thank you for returning the movie";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }

    //Test 1.12
    @Test
    public void testGetAMovieCorrectlyAnReturnADifferentOne()
      {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setMoviesContainer(getDummyContainer());
        biblioteca.getAnswer("");
        biblioteca.getAnswer("2");
        String sActualOutput = biblioteca.getAnswer("The Lord of the Rings");
        String sExpectedOutput = "Enjoy the movie!";
        assertEquals(sExpectedOutput, sActualOutput);
        //Back to the menu & return the movie
        biblioteca.getAnswer("3");
        String sActualOutput2 = biblioteca.getAnswer("The call of the Wild");
        String sExpectedOutput2 = "Sorry that movie does not belong to Biblioteca";
        assertEquals(sExpectedOutput2, sActualOutput2);
      }


  }
