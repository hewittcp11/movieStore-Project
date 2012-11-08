// MovieReport.java by Clark Hewitt 10/28/2012.
// This class represents a single movie report showing which movies are selling.

import java.util.*;

public class MovieReport
{
  int movieId;
  String movieName;
  String movieYear;
  int qty;
  double moviePrice;


  public MovieReport (int mid, String name, String yr, int q, double mp)
  {
    movieId = mid;
    movieName = name;
    movieYear = yr;
    qty = q;
    moviePrice = mp;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public String getMovieName()
  {
    return movieName;
  }

  public String getMovieYear()
  {
    return movieYear;
  }

  public int getQty()
  {
    return qty;
  }

  public double getMoviePrice()
  {
    return moviePrice;
  }

  public String toString()
  {
    return movieId + ", " + movieName + ", " + movieYear + ", " + qty + ", " + moviePrice;
  }
}
