package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
    {
        public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore\n";
        public static final String MENU_OPTIONS = "This is the menu, you can select: \n '1. List of Books' \n '2. Checkout a Book' \n '0. Quit'";
        private List<Book> bookList;
        private String bibliotecaState = "IDLE";

        public static void main(String[] args)
            {
                Scanner inputOption = new Scanner(System.in);
                BibliotecaApp biblioteca = new BibliotecaApp();

                System.out.println("" + biblioteca.getGreeting() + biblioteca.getMenu());
                boolean running = true;
                List bookList = new ArrayList();
                bookList.add(new Book("Aldous Huxley", "A brave new world", 1932, true));
                bookList.add(new Book("J. R. R. Tolkien", "The Lord of the Rings", 1954, true));
                biblioteca.setBooks(bookList);
                String userInput;

                while (running)
                    {
                        userInput = inputOption.nextLine();
                        String bibliotecaAnswer = biblioteca.getAnswer(userInput);
                        biblioteca.printMessage(bibliotecaAnswer);
                        if (userInput.toLowerCase().contains("0"))
                            {
                                running = false;
                            }
                    }
            }


        public String getAnswer(String _sUserInput)
            {
                if (bibliotecaState.equals("CHECKOUT_MENU"))
                    {
                        return handleCheckOutMenu(_sUserInput);
                    } else if (bibliotecaState.equals("RETURN_MENU"))
                    {
                        return handleReturnMenu(_sUserInput);
                    } else
                    {
                        if (_sUserInput.equals(""))
                            {
                                return getGreeting() + getMenu();
                            } else if (_sUserInput.equals("1"))
                            {
                                return getBookList();
                            } else if (_sUserInput.equals("2"))
                            {
                                bibliotecaState = "CHECKOUT_MENU";
                                return "Please write the name of the Book that you want to Check-Out";
                            } else if (_sUserInput.equals("3"))
                            {
                                bibliotecaState = "RETURN_MENU";
                                return "Please write the name of the Book that you want to Return";
                            } else if (_sUserInput.equals("0"))
                            {
                                return "Thanks for using Biblioteca!";
                            } else
                            {
                                return "Please select a valid option!\n" + getMenu();
                            }

                    }

            }

        private String handleCheckOutMenu(String _sUserInput)
            {
                String bookName = _sUserInput.toLowerCase();
                boolean foundBook = false;
                boolean available = false;

                for (Book b : bookList)
                    {
                        if (b.getNameOfTheBook().toLowerCase().equals(bookName))
                            {
                                foundBook = true;

                                if (b.getAvailabilityOfTheBook())
                                    {
                                        available = true;
                                        b.setAvailabilityOfTheBook(false);
                                    }
                                break;
                            }
                    }
                bibliotecaState = "IDLE";
                if (foundBook && available)
                    {
                        return "Enjoy the book!";
                    } else
                    {
                        return "Sorry that book is not available";
                    }
            }

        private String handleReturnMenu(String _sUserInput)
            {
                String bookName = _sUserInput.toLowerCase();
                boolean foundBook = false;
                boolean notAvailable = true;

                for (Book b : bookList)
                    {
                        if (b.getNameOfTheBook().toLowerCase().equals(bookName))
                            {
                                foundBook = true;

                                if (b.getAvailabilityOfTheBook())
                                    {
                                        notAvailable = false;
                                        b.setAvailabilityOfTheBook(true);
                                    }
                                break;
                            }
                    }
                bibliotecaState = "IDLE";
                if (foundBook && notAvailable)
                    {
                        return "Thank you for returning the book";
                    } else
                    {
                        return "Sorry that book does not belong to Biblioteca";
                    }
            }


        public void setBooks(List bookList)
            {
                this.bookList = bookList;

            }

        private String getGreeting()
            {
                return WELCOME;
            }

        private String getMenu()
            {
                return MENU_OPTIONS;
            }


        public String getBookList()
            {
                String resultList = "";
                for (Book b : bookList)
                    {
                        if (b.getAvailabilityOfTheBook())
                            {
                                resultList = resultList + b.getAuthorOfTheBook() + " : " + b.getNameOfTheBook() + " : "
                                        + b.getYearOfPublishing() + " " + "\n";
                            }
                    }
                return resultList;
            }


        public String printMessage(String message)
            {
                System.out.println(message);
                return message;
            }


    }
