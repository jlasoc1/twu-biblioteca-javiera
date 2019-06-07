package com.twu.biblioteca;

public class App
  {
    public static void main(String[] args)
      {
        BibliotecaApp biblioteca = new BibliotecaApp(System.in, System.out);
        biblioteca.loadData();
        biblioteca.Start();
      }
  }
