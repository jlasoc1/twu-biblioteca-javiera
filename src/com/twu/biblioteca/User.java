package com.twu.biblioteca;

import java.util.List;

public class User extends Item
  {

    private String userLibraryNumber;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private List<Item> userListOfCheckedOutList;

    public User(String userLibraryNumber, String userName, String userPassword, String userEmail, List<Item> userListOfCheckedOutList, String userPhone)
      {
        this.userLibraryNumber = userLibraryNumber;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userListOfCheckedOutList = userListOfCheckedOutList;
      }

    public String getUserLibraryNumber()
      {
        return userLibraryNumber;
      }

    public String getUserPassword()
      {
        return userPassword;
      }

    public String getUserPhone()
      {
        return userPhone;
      }

    public String getUserEmail()
      {
        return userEmail;
      }

    public List<Item> getUserListOfCheckedOutList()
      {
        return userListOfCheckedOutList;
      }

    @Override
    public boolean isAvailable()
      {
        return false;
      }

    @Override
    public void setAvailability(boolean availability)
      {

      }

    @Override
    public String getName()
      {
        return userName;
      }

    @Override
    public String list()
      {
        return this.getName() + " : " + this.getUserEmail() + " : "
                + this.getUserPhone() + " " + "\n";
      }

    @Override
    public String setUserListOfCheckedOutList(User listOfCheckedOut)
      {
        return "";
      }

    public void addItem(Item i)
      {
        userListOfCheckedOutList.add(i);
      }
  }
