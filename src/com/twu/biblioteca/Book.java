package com.twu.biblioteca;


public class Book extends Item
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




    public String getAuthorOfTheBook()
      {
        return authorOfTheBook;
      }

    @Override
    public String getName()
      {
        return nameOfTheBook;
      }

    public int getYearOfPublishing()
      {
        return yearOfPublishing;
      }

    @Override
    public boolean isAvailable()
      {
        return availabilityOfTheBook;
      }


    //setter
    @Override
    public void setAvailability(boolean availabilityOfTheBook)
      {
        this.availabilityOfTheBook = availabilityOfTheBook;
      }

    @Override
    public String list()
      {
        return this.getAuthorOfTheBook() + " : " + this.getName() + " : "
                + this.getYearOfPublishing() + " " + "\n";

      }

    @Override
    public String setUserListOfCheckedOutList(User listOfCheckedOut)
      {
        return "";

      }

  }


