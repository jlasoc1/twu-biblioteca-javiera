package com.twu.biblioteca;


public class Book
{
    private String authorOfTheBook;
    private String nameOfTheBook;
    private int yearOfPublishing;



    public Book(String authorOfTheBook, String nameOfTheBook, int yearOfPublishing)
    {
        this.authorOfTheBook = authorOfTheBook;
        this.nameOfTheBook = nameOfTheBook;
        this.yearOfPublishing = yearOfPublishing;
    }



// getters

    public String getAuthorOfTheBook()
    {
        return authorOfTheBook;
    }

    public String getNameOfTheBook()
    {
        return nameOfTheBook;
    }

    public int getYearOfPublishing()
    {
        return yearOfPublishing;
    }
}


