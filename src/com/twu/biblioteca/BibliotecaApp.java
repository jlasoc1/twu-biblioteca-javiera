package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
{
    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    public static final String MENU_OPTIONS = "This is the menu, you can select: \n '1. List of Books' \n '2. Checkout a Book' \n '0. Quit'";
    private List<Book> bookList;

    public static void main(String[] args)
    {
        Scanner inputOption = new Scanner(System.in);
        BibliotecaApp biblioteca = new BibliotecaApp();

        System.out.println("" + biblioteca.getGreeting() + biblioteca.getMenu());
        boolean running = true;
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954));
        biblioteca.setBooks(bookList);
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


    public String getAnswer(String _sUserInput)
    {
        if (_sUserInput.equals(""))
        {
            return getGreeting() + getMenu();
        }
        else if (_sUserInput.equals("1"))
        {
            return getBookList();
        }
        else if (_sUserInput.equals("2"))
        {
            return "Please write the name of the Book that you want to Check-Out";
        }

        else if (_sUserInput.equals("0"))
        {
            return "Thanks for using Biblioteca!";
        }
        else
        {
            return "Please select a valid option!\n" + getMenu();
        }
    }


    public void setBooks(List bookList)
    {
        this.bookList = bookList;

    }

    private String getGreeting()
    {
        return WELCOME;
    }

    private String getMenu()
    {
        return MENU_OPTIONS;
    }


    public String getBookList()
    {
        String resultList = "";
        for (Book b : bookList)
        {
            resultList = resultList + b.getAuthorOfTheBook() + " : " + b.getNameOfTheBook() + " : "
                    + b.getYearOfPublishing() + " " + "\n" ;
        }
        return resultList;
    }


    public String printMessage(String message) {
        System.out.println(message);
        return message;
    }

}
