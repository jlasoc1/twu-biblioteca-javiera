package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp
{

    private List<Book> bookList;

    public static void main(String[] args)
    {

    }

    public String getAnswer(String _sUserInput)
    {
        return getGreeting() + getMenu();
    }


    public void setBooks(List bookList)
    {
        this.bookList = bookList;

    }

    private String getGreeting()
    {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
    }

    private String getMenu()
    {
        return "This is the menu, you can select: \n '1. List of Books'";
    }


    public String getBookList()
    {
        String resultList = "{";
        for (Book b : bookList)
        {
            resultList = resultList + b.getAuthorOfTheBook() + " : " + b.getNameOfTheBook() + " : "
                    + b.getYearOfPublishing() + " " + "\n" ;

        }
        resultList = resultList + "}";

        return resultList;
    }


}
