// StoreApp.java by John Phillips on 9/3/2012 revised by Clark Hewitt 9/26/2012
// This code is a second pass at a database application for a digital movie store.

import java.sql.*;
import java.util.*;
import java.text.*;

public class StoreApp
{
  DB mydb;
  Scanner sc;

  public StoreApp()
  {
    mydb = new DB();
    sc = new Scanner(System.in);
  }

  public void showMenu()
  {
    System.out.println();
    System.out.println("1 = Add customer");
    System.out.println("2 = List customers");
    System.out.println("3 = Find customer");
    System.out.println("4 = Add movie");
    System.out.println("5 = List movies");
    System.out.println("6 = Find movie");
    System.out.println("7 = Make a sale");
    System.out.println("8 = Print sales report");
    System.out.println("9 = Print movie report");
    System.out.println("10 = Print customer report");
    System.out.println("0 = Quit");
  }

  public void mainLoop() throws Exception
  {
    int choice = 999;
    while (choice != 0) {
      showMenu();
      choice = Validator.getInt(sc, "Enter choice: ", 0, 10);
      if (1 == choice) addCustomer();
      else if (2 == choice) listCustomers();
      else if (3 == choice) findCustomerByName();
      else if (4 == choice) addMovie();
      else if (5 == choice) listMovies();
      else if (6 == choice) findMovie();
      else if (7 == choice) makeSale();
      else if (8 == choice) SalesReport();
      else if (9 == choice) MovieReport();
      else if (10 == choice) CustomerReport();
      else if (0 == choice) ;
      else System.out.println("\nInvalid Choice. Please try again.\n");
    }
  }

  public void addCustomer()
  {
    int custId = 0;
    String first = Validator.getLine(sc, "Enter customer first name: ");
    String last  = Validator.getLine(sc, "Enter last name: ");
    String phone = Validator.getLine(sc, "Enter phone number (111-222-3333): ");
    Customer c = new Customer(custId, first, last, phone);
    String result = mydb.addCustomer(c);
    System.out.println(result);
  }

  public void listCustomers()
  {
    int orderBy =
      Validator.getInt(sc, "1 = sort by custId, 2 = sort by name: ", 1, 2);
    int startRecord =
      Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
      Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<Customer> cList =
      mydb.listCustomers(orderBy, startRecord, numberToDisplay);
    
    System.out.println();
    System.out.printf("%10s %-20s %-20s %12s\n", "custId", "First Name", "Last Name", "Phone");
    for (Customer c : cList)
    {
      System.out.printf("%10s %-20s %-20s %15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getPhone());
    }
  }

  public void findCustomerByName()
  {
    String first = Validator.getLine(sc, "Enter customer first name: ");
    String last  = Validator.getLine(sc, "Enter last name: ");

    ArrayList<Customer> cList = mydb.findCustomerByName(first, last);

    for (Customer c : cList)
    {
      System.out.printf("%-10s %-20s %-20s %-15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getPhone());
    }
  }

  public void addMovie()
  {
    int movieId = 0;
    String movieName = Validator.getLine(sc, "Enter movie name: ");
    String movieYear = Validator.getLine(sc, "Enter movie release year: ");
    double retailPrice = Validator.getDouble(sc, "Enter retail price: ");
    String movieSize = Validator.getLine(sc, "Enter movie file size: ");
    Movie m = new Movie(movieId, movieName, movieYear, retailPrice, movieSize);
    String result = mydb.addMovie(m);
    System.out.println(result);
  }

  public void listMovies()
  {
    int orderBy =
      Validator.getInt(sc, "1 = sort by movieId, 2 = sort by movieName: ", 1, 2);
    int startRecord =
      Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
      Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<Movie> mList =
      mydb.listMovies(orderBy, startRecord, numberToDisplay);

    System.out.println();
    System.out.printf("%8s %20s %21s %10s %8s\n", "movieId", "Movie Name", "Year", "Price", "Size");
    for (Movie m : mList)
    {
      System.out.printf("%8s %-35s %6s %10s %8s\n",
          m.getMovieId(), m.getMovieName(), m.getMovieYear(),
          m.getMoviePrice(), m.getMovieSize());
    }
  }

  public void findMovie()
  {
    String movieName = Validator.getLine(sc, "Enter movie name: ");
    int movieId  = Validator.getInt(sc, "Enter movieId: ");

    ArrayList<Movie> mList = mydb.findMovie(movieName, movieId);

    for (Movie m : mList)
    {
      System.out.printf("%-8s %-35s %-6s %-10s %-8s\n",
          m.getMovieId(), m.getMovieName(), m.getMovieYear(),
          m.getMoviePrice(), m.getMovieSize());
    }
  }

  public void makeSale()
  {
    int custId, movieId, qty, totalItems=0, count=0;
    double price, totalSale=0.00;
    String choice = "y", customer, date;
    ArrayList<LineItem> lines = new ArrayList<LineItem>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    custId = Validator.getInt(sc, "Enter customer id: ", 1, 999999999);
    date = sdf.format(new java.util.Date());

    while (choice.equalsIgnoreCase("y"))
    {
      count++;
      movieId = Validator.getInt(sc, ("Movie id " + count + ": "), 1, 999999999);
      qty = Validator.getInt(sc, "Quantity: ", 0, 999999999);
      price = Validator.getDouble(sc, "Price: ", 0, 999999999);
      lines.add(new LineItem(0, movieId, qty, price));

      totalItems = count;
      totalSale = totalSale + price;
      
      choice = Validator.getLine(sc, "Another item (y/n)? ");
    }

    Sale sale = new Sale(0, date, custId, lines, totalItems, totalSale);
    mydb.addSale(sale);

    System.out.println();
    System.out.println("Sale added with " + totalItems + " movies sold for total of $ " + totalSale + ".");
  }



  public void SalesReport()
  {
    int orderBy =
      Validator.getInt(sc, "1 = sort by saleId, 2 = sort by movieId: ", 1, 2);
    int startRecord =
      Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
      Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<SalesReport> srList =
      mydb.SalesReport(orderBy, startRecord, numberToDisplay); 

    System.out.println();
    System.out.println("Who, on what date, bought movies?");
    System.out.printf("%8s %8s %12s %11s %8s\n", "SaleId", "CustId", "Date", "MovieId", "Price");
    for (SalesReport sr : srList)
    {
      System.out.printf("%8s %8s %15s %8s %8s\n",
          sr.getSaleId(), sr.getCustId(), sr.getDateTime(), sr.getMovieId(), sr.getMoviePrice());
    }   
  }





  public void MovieReport()
  {
    int count = 0;

    int orderBy =
      Validator.getInt(sc, "1 = sort by movieId, 2 = sort by movieName: ", 1, 2);
    int startRecord =
      Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
      Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<MovieReport> mrList =
      mydb.MovieReport(orderBy, startRecord, numberToDisplay);

    System.out.println();
    System.out.println("Which movies are selling?");
    System.out.printf("%8s %20s %21s %10s %8s\n", "movieId", "Movie Name", "Year", "Qty", "Price");
    for (MovieReport mr : mrList)
    {
      System.out.printf("%8s %-35s %6s %10s %8s\n",
          mr.getMovieId(), mr.getMovieName(), mr.getMovieYear(),
          mr.getQty(), mr.getMoviePrice());
    }
  }





  public void CustomerReport()
  {
  int orderBy =
      Validator.getInt(sc, "1 = sort by custId, 2 = sort by name: ", 1, 2);
    int startRecord =
      Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
      Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<CustomerReport> crList =
      mydb.CustomerReport(orderBy, startRecord, numberToDisplay); 

    System.out.println();
    System.out.println("Which customers have bought movies?");
    System.out.printf("%8s %-15s %-15s %12s\n", "CustId", "First Name", "Last Name", "Date");
    for (CustomerReport cr : crList)
    {
      System.out.printf("%8s %-15s %-15s %15s\n",
          cr.getCustId(), cr.getFirstName(), cr.getLastName(), cr.getDateTime());
    }   
  }




  public static void main(String[] args) throws Exception
  {
    StoreApp store = new StoreApp();
    store.mainLoop();
  }
}
