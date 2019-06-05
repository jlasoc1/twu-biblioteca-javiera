package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyAccountTest
  {

    public Container getDummyContainer()
      {
        List<Item> movieList = new ArrayList();
        movieList.add(new Movie("A Clockwork Orange", 1971, "Stanley Kubrick", 8, true, true));
        movieList.add(new Movie("Big Fish", 2003, "Tim Burton", 8, true, true));

        List userList = new ArrayList();
        userList.add(new User("100-0001", "Sebastián Céspedes", "123abc",
                "scespedes@thoughtworks.com", movieList, "+56975647764"));
        userList.add(new User("100-0002", "Andrés Fuentes", "123abc",
                "afuentes@thoughtworks.com", movieList, "+56945621345"));
        return new Container(userList);
      }


    @Test
    public void shouldGetWelcomeMessageWhenIChooseMyAccountSection()
      {
        String sExpectedOutput = "This is your menu, you can select: \n '1. My information' \n " +
                "'2. Books that I've checked-out' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.getAnswer("");
        String sActualOutput = biblioteca.getAnswer("3");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    @Test
    public void shouldGetMyInformationWhenIChooseOptionOne()
      {
        String sExpectedOutput = " Sebastián Céspedes : scespedes@thoughtworks.com : +56975647764";
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.getAnswer("");
        biblioteca.getAnswer("3");
        String sActualOutput = biblioteca.getAnswer("1");
        assertEquals(sExpectedOutput, sActualOutput);
      }

    }

