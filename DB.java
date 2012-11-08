// DB.java by John Phillips on 9/3/2012 revised by Clark Hewitt 9/26/2012
// Contains the database methods for the digital movie store program.

import java.sql.*;
import java.util.ArrayList;

// Be sure to change the dbURL/username/password to your own!

public class DB {
  private Connection connect = null;
  private String dbURL = "jdbc:mysql://localhost/hewittcp11";
  private String username = "hewittcp11";
  private String password = "hewittcp11";

  public DB() {
    getConnection();
  }

  private void getConnection()
  {
    try
    {
      connect = DriverManager.getConnection(dbURL, username, password);
    }
    catch (SQLException e)
    {
      System.out.println("Exception thrown calling getConnection.\n" + e.getMessage());
    }
  }

  public String addCustomer(Customer c)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into customer (custId, firstName, lastName, phone) "
        + "values (null, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setString(1, c.getFirstName());
      ps.setString(2, c.getLastName());
      ps.setString(3, c.getPhone());

      ps.executeUpdate();
      result = c.getFirstName() + " " + c.getLastName() + " "
      + c.getPhone() + " has been added to customer table.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }

  public ArrayList<Customer> listCustomers(int orderBy,
      int startRecord, int numberToDisplay)
  {
    ArrayList<Customer> cList = new ArrayList<Customer>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select custId, firstName, lastName, phone from customer "
          + "order by custId limit ?, ?";
      }
      else
      {
        q = "select custId, firstName, lastName, phone from customer "
          + "order by lastName, firstName limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        cList.add( new Customer(
              rs.getInt("custId"), rs.getString("firstName"), rs.getString("lastName"),
              rs.getString("phone") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return cList;
  }

  public ArrayList<Customer> findCustomerByName(String first, String last)
  {
    ArrayList<Customer> cList = new ArrayList<Customer>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "select custId, firstName, lastName, phone from customer "
          + "where firstName like ? and lastName like ? order by custId";
      ps = connect.prepareStatement(q);
      ps.setString(1, first + "%");
      ps.setString(2, last + "%");
      rs = ps.executeQuery();

      while (rs.next())
      {
        cList.add( new Customer(
              rs.getInt("custId"), rs.getString("firstName"), rs.getString("lastName"),
              rs.getString("phone") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return cList;
  }

  public String addMovie(Movie m)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into movie (movieId, movieName, movieYear, moviePrice, movieSize) "
        + "values (null, ?, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setString(1, m.getMovieName());
      ps.setString(2, m.getMovieYear());
      ps.setDouble(3, m.getMoviePrice());
      ps.setString(4, m.getMovieSize());
      ps.executeUpdate();
      result = m.getMovieName() + " at " + m.getMoviePrice() + " has been added to stock.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }


public ArrayList<Movie> listMovies(int orderBy,
      int startRecord, int numberToDisplay)
  {
    ArrayList<Movie> mList = new ArrayList<Movie>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select movieId, movieName, movieYear, moviePrice, movieSize from movie "
          + "order by movieId limit ?, ?";
      }
      else
      {
        q = "select movieId, movieName, movieYear, moviePrice, movieSize from movie "
          + "order by movieName limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        mList.add( new Movie(
              rs.getInt("movieId"), rs.getString("movieName"), rs.getString("movieYear"),
              rs.getDouble("moviePrice"), rs.getString("movieSize") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return mList;
  }


  public ArrayList<Movie> findMovie(String movieName, int movieId)
  {
    ArrayList<Movie> mList = new ArrayList<Movie>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "select movieId, movieName, movieYear, moviePrice, movieSize from movie "
          + "where movieName like ? order by movieId";
      ps = connect.prepareStatement(q);
      ps.setString(1, movieName + "%");
      rs = ps.executeQuery();

      while (rs.next())
      {
        mList.add( new Movie(rs.getInt("movieId"), rs.getString("movieName"),
              rs.getString("movieYear"), rs.getDouble("moviePrice"),
              rs.getString("movieSize") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return mList;
  }

  public void addSale(Sale sale)
  {
	  String result = "" , q = "";
	  PreparedStatement ps = null;
	  ResultSet rs = null;

	  try
	  {
		  q = "insert into sale (saleId, date, custId, totalItems, totalSale) "
		  	+ "values (null, ?, ?, ?, ?)";
		  ps = connect.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
		  ps.setString(1, sale.getDateTime());
		  ps.setInt(2, sale.getCustId());
      ps.setDouble(3, sale.getTotalItems());
      ps.setDouble(4, sale.getTotalSale());
		  ps.executeUpdate();

		  rs = ps.getGeneratedKeys();
		  int saleId = -1;
		  if (rs.next())
		  {
			  saleId = rs.getInt(1);

			  q = "insert into transact (saleId, movieId, qty, moviePrice) "
				  + "values (?, ?, ?, ?)";
			  ps = connect.prepareStatement(q);

			  for (LineItem line : sale.getLines())
			  {
				  ps.setInt(1, saleId);
				  ps.setInt(2, line.getMovieId());
				  ps.setInt(3, line.getQty());
				  ps.setDouble(4, line.getMoviePrice());
				  ps.executeUpdate();
			  }
		  }
		  else System.out.println("Error getting saleId");
	  }
	  catch (SQLException e)
	  {
		  System.out.println("SQLException: " + e.getMessage());
		  System.out.println("|nQUERY: " + ps.toString());
	  }
  }





  public ArrayList<SalesReport> SalesReport(int orderBy,
      int startRecord, int numberToDisplay)
  {
    ArrayList<SalesReport> srList = new ArrayList<SalesReport>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select s.saleId, s.custId, s.date, t.movieId, t.moviePrice "
          + "from sale s join transact t on s.saleId = t.saleId "
          + "order by saleId limit ?, ?";
      }
      else
      {
        q = "select s.saleId, s.custId, s.date, t.movieId, t.moviePrice  "
          + "from sale s join transact t on s.saleId = t.saleId "
          + "order by movieId limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        srList.add( new SalesReport(
              rs.getInt("s.saleId"), rs.getInt("s.custId"), rs.getDate("s.date"),
              rs.getInt("t.movieId"), rs.getDouble("t.moviePrice") ) );
      }  
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return srList;
  }



  public ArrayList<MovieReport> MovieReport(int orderBy,
      int startRecord, int numberToDisplay)
  {
    ArrayList<MovieReport> mrList = new ArrayList<MovieReport>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select m.movieId, m.movieName, m.movieYear, t.qty, t.moviePrice "
          + "from movie m join transact t on m.movieId = t.movieId "
          + "order by m.movieId limit ?, ?";
      }
      else
      {
        q = "select m.movieId, m.movieName, m.movieYear, t.qty, t.moviePrice "
          + "from movie m join transact t on m.movieId = t.movieId "
          + "order by m.movieName limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        mrList.add( new MovieReport(
              rs.getInt("m.movieId"), rs.getString("m.movieName"), rs.getString("m.movieYear"),
              rs.getInt("t.qty"), rs.getDouble("t.moviePrice") ) );
      } 
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return mrList;
  }



  public ArrayList<CustomerReport> CustomerReport(int orderBy,
      int startRecord, int numberToDisplay)
  {
    ArrayList<CustomerReport> crList = new ArrayList<CustomerReport>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select c.custId, c.firstName, c.lastName, s.date "
          + "from customer c join sale s on c.custId = s.custId "
          + "order by custId limit ?, ?";
      }
      else
      {
        q = "select c.custId, c.firstName, c.lastName, s.date "
          + "from customer c join sale s on c.custId = s.custId "
          + "order by lastName, firstName limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        crList.add( new CustomerReport(
              rs.getInt("c.custId"), rs.getString("c.firstName"), rs.getString("c.lastName"),
              rs.getDate("s.date") ) );
      } 
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return crList;
  }

  public static void main(String[] args) throws Exception
  {
    DB mydb = new DB();

    ArrayList<Customer> cList = mydb.listCustomers(2, 1, 20);

    for (Customer c : cList)
    {
      System.out.printf("%10s, %20s, %20s, %15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getPhone());
    }
  }
}
