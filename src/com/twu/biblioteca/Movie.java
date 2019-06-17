package com.twu.biblioteca;

public class Movie extends Item
  {
    private String nameOfTheMovie;
    private int yearOfPremiere;
    private String directorOfTheMovie;
    private int ratingOfTheMovie;
    private boolean isRatedTheMovie;
    private boolean availabilityOfTheMovie;


    public Movie(String nameOfTheMovie, int yearOfPremiere, String directorOfTheMovie, int ratingOfTheMovie, boolean isRatedTheMovie, boolean availabilityOfTheMovie)
      {
        this.nameOfTheMovie = nameOfTheMovie;
        this.yearOfPremiere = yearOfPremiere;
        this.directorOfTheMovie = directorOfTheMovie;
        this.ratingOfTheMovie = ratingOfTheMovie;
        this.isRatedTheMovie = isRatedTheMovie;
        this.availabilityOfTheMovie = availabilityOfTheMovie;
      }


    public int getYearOfPremiere()
      {
        return yearOfPremiere;
      }

    public String getName()
      {
        return nameOfTheMovie;
      }

    public String getDirectorOfTheMovie()
      {
        return directorOfTheMovie;
      }

    @Override
    public boolean isAvailable()
      {
        return availabilityOfTheMovie;
      }

    @Override
    public void setAvailability(boolean availability)
      {
        this.availabilityOfTheMovie = availability;
      }

    @Override
    public String list()
      {
        String sep = " : ";
        return this.getName() + sep + this.getYearOfPremiere() + sep
                + this.getDirectorOfTheMovie() + sep + this.ratingOfTheMovie + "\n";

      }

    @Override
    public String setUserListOfCheckedOutList(User listOfCheckedOut)
      {
        return "";

      }
  }
