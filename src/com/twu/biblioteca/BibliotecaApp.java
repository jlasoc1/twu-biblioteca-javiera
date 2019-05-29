package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
{
    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    public static final String LIST_OF_BOOKS = "This is the menu, you can select: \n '1. List of Books'";
    private List<Book> bookList;

    public static void main(String[] args)
    {
        Scanner myObj = new Scanner(System.in);
        boolean running = true;
        String userInput = "";
        BibliotecaApp biblioteca = new BibliotecaApp();
        List bookList = new ArrayList();
        bookList.add(new Book("Aldous Huxley", "A brave new world", 1932 ));
        bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954 ));
        biblioteca.setBooks(bookList);

        while (running)
        {
            String bibliotecaAnswer = biblioteca.getAnswer(userInput);
            biblioteca.printMessage(bibliotecaAnswer);
            if (bibliotecaAnswer.toLowerCase().contains("option!"))
            {
                running = false;
            }
            else {
                userInput = myObj.nextLine();
            }
        }
    }

    public String printMessage(String message) {
        System.out.println(message);
        return message;
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
        else if (_sUserInput.equals(2))
        {
            return "Please select a valid option!\n" + getMenu();
        }
        else if (_sUserInput.equals("0"))
        {
            return "Thanks for using Biblioteca!";
        }
        else
        {
            return ("");
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
        return LIST_OF_BOOKS;
    }


    public String getBookList()
    {
        String resultList = "";
        for (Book b : bookList)
        {
            resultList = resultList + b.getAuthorOfTheBook() + " : " + b.getNameOfTheBook() + " : "
                    + b.getYearOfPublishing() + " " + "\n" ;
        }
        resultList = resultList;

        return resultList;
    }


}
