package com.twu.biblioteca;


public class Book
{
    private String authorOfTheBook;
    private String nameOfTheBook;
    private int yearOfPublishing;
    private boolean availabilityOfTheBook;



    public Book(String authorOfTheBook, String nameOfTheBook, int yearOfPublishing, boolean availabilityOfTheBook)
    {
        this.authorOfTheBook = authorOfTheBook;
        this.nameOfTheBook = nameOfTheBook;
        this.yearOfPublishing = yearOfPublishing;
        this.availabilityOfTheBook = availabilityOfTheBook;
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

    public boolean getAvailabilityOfTheBook() {return availabilityOfTheBook;}


    //setter

    public void setAvailabilityOfTheBook(boolean availabilityOfTheBook)
    {
        this.availabilityOfTheBook = availabilityOfTheBook;
    }
}


