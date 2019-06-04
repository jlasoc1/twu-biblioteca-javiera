package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
  {
    private enum BibliotecaState
      {
        IDLE,
        CHECKOUT_MENU,
        RETURN_MENU
      }

    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    public static final String MENU_OPTIONS = "This is the menu, you can select: \n '1. List of Books' \n " +
            "'2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
    private BibliotecaState bibliotecaState = BibliotecaState.IDLE;
    private Container bookContainer;
    private Container movieContainer;

    public static void main(String[] args)
      {
        Scanner inputOption = new Scanner(System.in);
        BibliotecaApp biblioteca = new BibliotecaApp();

        System.out.println("" + biblioteca.getGreeting() + biblioteca.getMenu());

        boolean running = true;
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        biblioteca.setBooksContainer(new Container(bookList));
        String userInput;

        while (running)
          {
            userInput = inputOption.nextLine();
            String bibliotecaAnswer = biblioteca.getAnswer(userInput);
            biblioteca.printMessage(bibliotecaAnswer);
            if (userInput.toLowerCase().contains("0"))
              {
                running = false;
              }
          }

      }

    public void setBooksContainer(Container container)
      {
        bookContainer = container;
      }

    public void setMoviesContainer(Container container)
      {
        movieContainer = container;
      }




    public String getAnswer(String _sUserInput)
      {
        if (bibliotecaState == BibliotecaState.CHECKOUT_MENU)
          {
            return handleCheckOutMenu(bookContainer, _sUserInput, "movie");
          } else if (bibliotecaState == BibliotecaState.RETURN_MENU)
          {
            return handleReturnMenu(bookContainer, _sUserInput, "movie");
          } else
          {
            if (_sUserInput.equals(""))
              {
                return getGreeting() + getMenu();
              } else if (_sUserInput.equals("1"))
              {
                return bookContainer.list();
              } else if (_sUserInput.equals("2"))
              {
                bibliotecaState = BibliotecaState.CHECKOUT_MENU;
                return "Please write the name of the Book that you want to Check-Out";
              } else if (_sUserInput.equals("3"))
              {
                bibliotecaState = BibliotecaState.RETURN_MENU;
                return "Please write the name of the Book that you want to Return";
              } else if (_sUserInput.equals("0"))
              {
                return "Thanks for using Biblioteca!";
              } else
              {
                return "Please select a valid option!\n" + getMenu();
              }
          }
      }

    private String handleCheckOutMenu(Container _container, String _sUserInput, String _type)
      {
        String bookName = _sUserInput.toLowerCase();
        Item i = _container.checkout(bookName);
        bibliotecaState = BibliotecaState.IDLE;
        if (i != null)
          {
            return "Enjoy the " +_type+ "!";

          }
        return "Sorry that " +_type+ " is not available";
      }

    private String handleReturnMenu(Container _container, String _sUserInput, String _type)
      {
        String bookName = _sUserInput.toLowerCase();
        Item i = _container.returnItem(bookName);
        bibliotecaState = BibliotecaState.IDLE;
        if (i != null)
          {
            return "Thank you for returning the " +_type;
          }
        return "Sorry that " +_type+ " does not belong to Biblioteca";
      }


    private String getGreeting()
      {
        return WELCOME;
      }

    private String getMenu()
      {
        return MENU_OPTIONS;
      }


    public String printMessage(String message)
      {
        System.out.println(message);
        return message;
      }


  }
