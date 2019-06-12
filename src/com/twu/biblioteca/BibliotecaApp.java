package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
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
        MOVIE_MENU,
        MY_ACCOUNT_MENU,
        LOGIN
      }

    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    public static final String MENU_OPTIONS = "This is the menu, you can select: \n '1. List of Books' \n " +
            "'2. Check-out a Book' \n '3. Return a Book' \n '0. Quit'";
    public static final String MENU_MOVIE_OPTIONS = "This is the menu, you can select: \n '1. List of Movies' \n " +
            "'2. Check-out a Movie' \n '3. Return a Movie' \n '0. Quit'";
    public static final String MY_ACCOUNT_MENU = "This is your menu, you can select: \n '1. My information' \n " +
            "'2. Elements that I've checked-out' \n '0. Quit'";
    public static final String MAIN_MENU_OPTIONS = "Please select the right option for you\n '1. Books' \n " +
            "'2. Movies' \n '3. My Account' \n '0. Quit'";
    public static final String INVALID_INPUT_MESSAGE = "Please select a valid option!\n";
    public static final String EXIT_MESSAGE = "Thanks for using Biblioteca!";
    public static final String USER_LOGIN = "Please enter your user (xxx-xxxx) and password separated by a comma ','";

    private BibliotecaState bibliotecaState = BibliotecaState.LOGIN;
    private Container bookContainer;
    private Container movieContainer;
    private List<User> userList;
    private InputStream input;
    private PrintStream output;
    private User currentUser = null;


    public BibliotecaApp(InputStream in, PrintStream out)
      {
        this.input = in;
        this.output = out;
      }

    public BibliotecaApp()
      {
        this.input = System.in;
        this.output = System.out;
      }

    public void Start()
      {
        Scanner inputOption = new Scanner(this.input);
        boolean running = true;
        String userInput;

        this.printMessage(getGreeting() + getUserLoginMessage());


        while (running)
          {
            userInput = inputOption.nextLine();
            String bibliotecaAnswer = this.getAnswer(userInput);
            this.printMessage(bibliotecaAnswer);
            if (userInput.toLowerCase().equals("0"))
              {
                running = false;
              }
          }
      }

    public void loadData()
      {
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
        this.setBooksContainer(new Container(bookList));

        List movieList = new ArrayList();
        movieList.add(new Movie("A Clockwork Orange", 1971, "Stanley Kubrick", 8, true, true));
        movieList.add(new Movie("Big Fish", 2003, "Tim Burton", 8, true, true));
        this.setMoviesContainer(new Container(movieList));

        List userList = new ArrayList();
        userList.add(new User("100-0001", "Sebastián Céspedes", "123abc",
                "scespedes@thoughtworks.com", new ArrayList(), "+56975647764"));
        userList.add(new User("100-0002", "Andrés Fuentes", "123abc",
                "afuentes@thoughtworks.com", new ArrayList(), "+56945621345"));
        this.setUserList(userList);

      }

    public void setUserList(List userList)
      {
        this.userList = userList;
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
            case LOGIN:
            {
              String[] parts = _sUserInput.split(",");
              String user = parts[0];
              String pass = parts[1];
              User validatedUser = validateUserLogin(user, pass);
              if (validatedUser != null)
                {
                  currentUser = validatedUser;
                  bibliotecaState = BibliotecaState.GENERAL_MENU;
                  return getMainMenu();
                } else
                {
                  return getNotValidOptionMessage() + getUserLoginMessage();
                }
            }
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
                  case 3:
                  {
                    bibliotecaState = BibliotecaState.MY_ACCOUNT_MENU;
                    return getMyAccountMenu();

                  }
                  case 0:
                  {
                    return getExitMessage();
                  }
                  default:
                  {
                    return getNotValidOptionMessage() + getMainMenu();
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
            case MY_ACCOUNT_MENU:
            {
              int option = Integer.parseInt(_sUserInput);
              switch (option)
                {
                  case 1:
                  {
                    return getUserInformation();
                  }
                  case 2:
                  {
                    return getCheckedOutItems();
                  }
                  case 0:
                  {
                    return getExitMessage();
                  }
                  default:
                  {
                    return getNotValidOptionMessage() + getMyAccountMenu();
                  }
                }
            }
            default:
            {
              throw new RuntimeException("Not a valid state!");
            }
          }
      }

    private String getCheckedOutItems()
      {
        String result = "";
        for (Item i : currentUser.getUserListOfCheckedOutList())
          {
            result = result + i.list();
          }
        return result;
      }

    private String getUserInformation()
      {
        return currentUser.getName() + " : " + currentUser.getUserEmail() + " : " + currentUser.getUserPhone();
      }

    private String handleCheckOutMenu(Container _container, String _sUserInput, String _type)
      {
        String ItemName = _sUserInput.toLowerCase();
        Item i = _container.checkout(ItemName);
        bibliotecaState = BibliotecaState.GENERAL_MENU;
        if (i != null)
          {
            currentUser.addItem(i);
            return "Enjoy the " + _type + "! \n" + getMainMenu();
          }
        return "Sorry that " + _type + " is not available";
      }

    private String handleReturnMenu(Container _container, String _sUserInput, String _type)
      {
        String ItemName = _sUserInput.toLowerCase();
        Item i = _container.returnItem(ItemName);
        bibliotecaState = BibliotecaState.GENERAL_MENU;
        if (i != null)
          {
            return "Thank you for returning the " + _type + "! \n" + getMainMenu();
          }
        return "Sorry that " + _type + " does not belong to Biblioteca";
      }

    private User validateUserLogin(String _user, String _password)
      {
        for (User u : userList)
          {
            String user_aux = u.getUserLibraryNumber();
            String pass_aux = u.getUserPassword();
            if (_user.toLowerCase().equals(user_aux) && _password.toLowerCase().equals(pass_aux))
              {
                return u;
              }
          }
        return null;
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

    private String getMyAccountMenu()
      {
        return MY_ACCOUNT_MENU;
      }

    private String getExitMessage()
      {
        return EXIT_MESSAGE;
      }

    private String getNotValidOptionMessage()
      {
        return INVALID_INPUT_MESSAGE;
      }

    private String getUserLoginMessage()
      {
        return USER_LOGIN;
      }

    public void printMessage(String message)
      {
        this.output.println(message);
      }


  }
