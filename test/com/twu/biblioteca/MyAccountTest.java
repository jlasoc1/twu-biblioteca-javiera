package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyAccountTest
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
                "scespedes@thoughtworks.com", new ArrayList<Item>(), "+56975647764"));
        userList.add(new User("100-0002", "Andrés Fuentes", "kjah5463",
                "afuentes@thoughtworks.com", new ArrayList<Item>(), "+56945621345"));
        biblioteca.setUserList(userList);
        return biblioteca;
      }

    //Border Case
    @Test
    public void shouldPrintWelcomeMessageWhenBibliotecaStart()
      {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n" +
                "Please enter your user (xxx-xxxx) and password separated by a comma ','";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printMessage(expectedWelcomeMessage);
        assertEquals(expectedWelcomeMessage, outContent.toString().trim());
      }

    @Test
    public void shouldLoginAndShowGeneralMenuWhenTheUserAndPasswordExist()
      {
        String sExpectedOutput = "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        String sActualOutput = biblioteca.getAnswer("100-0001,123abc");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndShowGeneralMenuWhenTheUserDoesNotExist()
      {
        String sExpectedOutput = "Please select a valid option!\n" +
                "Please enter your user (xxx-xxxx) and password separated by a comma ','";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        String sActualOutput = biblioteca.getAnswer("100-8765,123abc");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndShowGeneralMenuWhenThePasswordDoesNotExist()
      {
        String sExpectedOutput = "Please select a valid option!\n" +
                "Please enter your user (xxx-xxxx) and password separated by a comma ','";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        String sActualOutput = biblioteca.getAnswer("100-0001,thOuGhtWorks");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndSelectTheBookMenu()
      {
        String sExpectedOutput = "This is the menu, you can select: \n '1. List of Books' \n " +
                "'2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndSelectTheMovieMenu()
      {
        String sExpectedOutput = "This is the menu, you can select: \n '1. List of Movies' \n " +
                "'2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndSelectTheMyAccountMenu()
      {
        String sExpectedOutput = "This is your menu, you can select: \n '1. My information' \n " +
                "'2. Elements that I've checked-out' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldLoginAndSelectAnInvalidOption()
      {
        String sExpectedOutput = "Please select a valid option!\n" + "Please select the right option for you\n '1. Books' \n " +
                "'2. Movies' \n '3. My Account' \n '0. Quit'";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        String sActualOutput = biblioteca.getAnswer("5");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldShowTheUserPersonalInformation()
      {
        String sExpectedOutput = "Sebastián Céspedes : scespedes@thoughtworks.com : +56975647764";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("3");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldShowTheUserBooksThatIHaveCheckedOut()
      {

        String sExpectedOutput = "Aldous Huxley : A brave new world : 1932 \n";
        BibliotecaApp biblioteca = getDummyBiblioteca();
        biblioteca.getAnswer("100-0001,123abc");
        biblioteca.getAnswer("1");
        biblioteca.getAnswer("2");
        biblioteca.getAnswer("A brave new world");
        biblioteca.getAnswer("3");
        String sActualOutput = biblioteca.getAnswer("2");
        assertEquals(sExpectedOutput, sActualOutput);
      }


  }

