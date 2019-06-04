package com.twu.biblioteca;

import java.util.List;

public class Container
  {
    private List<Item> itemList;

    public Container(List bookList)
      {
        this.itemList = bookList;

      }

    public String list()
      {
        String resultList = "";
        for (Item b : itemList)
          {
            if (b.isAvailable())
              {
                resultList = resultList + b.list();
              }
          }
        return resultList;
      }


    public Item checkout(String name)
      {
        for (Item b : itemList)
          {
            if (b.getName().toLowerCase().equals(name))
              {
                if (b.isAvailable())
                  {
                    b.setAvailability(false);
                    return b;
                  }

              }
          }
        return null;
      }

    public Item returnItem(String name)
      {
        for (Item b : itemList)
          {
            if (b.getName().toLowerCase().equals(name))
              {
                if (!b.isAvailable())
                  {
                    b.setAvailability(true);
                    return b;
                  }
              }
          }
        return null;
      }

  }
