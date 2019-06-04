package com.twu.biblioteca;

public abstract class Item
  {

    public abstract boolean isAvailable();

    public abstract void setAvailability(boolean availability);

    public abstract String getName();

    public abstract String list();
  }
