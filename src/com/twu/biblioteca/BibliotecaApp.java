package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
  {
    private enum BibliotecaState
      {
        IDLE,
        GENERAL_MENU,
        CHECKOUT_BOOK,
        RETURN_BOOK,
        CHECKOUT_MOVIE,
        RETURN_MOVIE,
        BOOK_MENU,
        MOVIE_MENU
      }

    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    public static final String MENU_OPTIONS = "This is the menu, you can select: \n '1. List of Books' \n " +
            "'2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
    public static final String MENU_MOVIE_OPTIONS = "This is the menu, you can select: \n '1. List of Movies' \n " +
            "'2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
    public static final String MAIN_MENU_OPTIONS = "Please select the right option for you\n '1. Books' \n " +
            "'2. Movies' \n '3. My Account' \n '0. Quit'";
    public static final String INVALID_INPUT_MESSAGE = "Please select a valid option!\n";
    public static final String EXIT_MESSAGE = "Thanks for using Biblioteca!";
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
        switch (bibliotecaState)
          {
            case IDLE:
            {
              bibliotecaState = BibliotecaState.GENERAL_MENU;
              return getGreeting() + getMainMenu();
            }
            case GENERAL_MENU:
            {
              int option = Integer.parseInt(_sUserInput);
              switch (option)
                {
                  case 1:
                  {
                    bibliotecaState = BibliotecaState.BOOK_MENU;
                    return getMenu();
                  }
                  case 2:
                  {
                    bibliotecaState = BibliotecaState.MOVIE_MENU;
                    return getMovieMenu();
                  }
                  case 0:
                  {
                    return getExitMessage();
                  }
                  default:
                  {
                    return getNotValidOptionMessage() + getMenu();
                  }
                }
            }
            case CHECKOUT_BOOK:
            {
              return handleCheckOutMenu(bookContainer, _sUserInput, "book");
            }
            case RETURN_BOOK:
            {
              return handleReturnMenu(bookContainer, _sUserInput, "book");
            }
            case CHECKOUT_MOVIE:
            {
              return handleCheckOutMenu(movieContainer, _sUserInput, "movie");
            }
            case RETURN_MOVIE:
            {
              return handleReturnMenu(movieContainer, _sUserInput, "movie");
            }
            case BOOK_MENU:
            {
              int option = Integer.parseInt(_sUserInput);
              switch (option)
                {
                  case 1:
                  {
                    return bookContainer.list();
                  }
                  case 2:
                  {
                    bibliotecaState = BibliotecaState.CHECKOUT_BOOK;
                    return "Please write the name of the Book that you want to Check-Out";
                  }
                  case 3:
                  {
                    bibliotecaState = BibliotecaState.RETURN_BOOK;
                    return "Please write the name of the Book that you want to Return";
                  }
                  case 0:
                  {
                    return getExitMessage();
                  }
                  default:
                  {
                    return getNotValidOptionMessage() + getMenu();
                  }
                }
            }
            case MOVIE_MENU:
            {
              int option = Integer.parseInt(_sUserInput);
              switch (option)
                {
                  case 1:
                  {
                    return movieContainer.list();
                  }
                  case 2:
                  {
                    bibliotecaState = BibliotecaState.CHECKOUT_MOVIE;
                    return "Please write the name of the Movie that you want to Check-Out";
                  }
                  case 3:
                  {
                    bibliotecaState = BibliotecaState.RETURN_MOVIE;
                    return "Please write the name of the Movie that you want to Return";
                  }
                  case 0:
                  {
                    return getExitMessage();
                  }
                  default:
                  {
                    return getNotValidOptionMessage() + getMovieMenu();
                  }
                }

            }
            default:
            {
              throw new RuntimeException("Not valid state!");
            }
          }


      }

    private String handleCheckOutMenu(Container _container, String _sUserInput, String _type)
      {
        String bookName = _sUserInput.toLowerCase();
        Item i = _container.checkout(bookName);
        bibliotecaState = BibliotecaState.GENERAL_MENU;
        if (i != null)
          {
            return "Enjoy the " + _type + "!";

          }
        return "Sorry that " + _type + " is not available";
      }

    private String handleReturnMenu(Container _container, String _sUserInput, String _type)
      {
        String bookName = _sUserInput.toLowerCase();
        Item i = _container.returnItem(bookName);
        bibliotecaState = BibliotecaState.GENERAL_MENU;
        if (i != null)
          {
            return "Thank you for returning the " + _type;
          }
        return "Sorry that " + _type + " does not belong to Biblioteca";
      }


    private String getGreeting()
      {
        return WELCOME;
      }

    private String getMainMenu()
      {
        return MAIN_MENU_OPTIONS;
      }

    private String getMenu()
      {
        return MENU_OPTIONS;
      }

    private String getMovieMenu()
      {
        return MENU_MOVIE_OPTIONS;
      }

    private String getExitMessage()
      {
        return EXIT_MESSAGE;
      }

    private String getNotValidOptionMessage()
      {
        return INVALID_INPUT_MESSAGE;
      }


    public String printMessage(String message)
      {
        System.out.println(message);
        return message;
      }


  }
