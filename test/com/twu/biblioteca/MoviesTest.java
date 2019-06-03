package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoviesTest
  {

    @Test
    public void shouldGetWelcomeMessageWhenNoOptionIsChoosed()
      {
        String sExpectedOutput = "Welcome to Movies section. This is the menu, you can select: \n '1. List of Movies' " +
                "\n '2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
        BibliotecaApp biblioteca = new BibliotecaApp();
        String sActualOutput = biblioteca.getAnswer("");
        assertEquals(sExpectedOutput, sActualOutput);
      }

  }
