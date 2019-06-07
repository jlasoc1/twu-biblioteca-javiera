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

    public User(String userLibraryNumber,String userName, String userPassword, String userEmail, List<Item> userListOfCheckedOutList, String userPhone)
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

    public String getUserName()
      {
        return userName;
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

    public void setUserListOfCheckedOutList(List<Item> userListOfCheckedOutList)
      {
        this.userListOfCheckedOutList = userListOfCheckedOutList;
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
        return null;
      }

    @Override
    public String list()
      {
        return this.getName() + " : " + this.getUserEmail() + " : "
                + this.getUserPhone() + " " + "\n";
      }


  }
