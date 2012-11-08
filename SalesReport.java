// SalesReport.java by Clark Hewitt 10/23/2012.
// This class represents a single sales report showing who, on what date, bought movies.

import java.util.*;
import java.text.*;

public class SalesReport
{
  int saleId;
  int custId;
  Date datetime;
  int movieId;
  double moviePrice;

  public SalesReport (int sid, int cid, Date dt, int mid, double mp)
  {
    saleId = sid;
    custId = cid;
    datetime = dt;
    movieId = mid;
    moviePrice = mp;
  }

  public int getSaleId()
  {
    return saleId;
  }

  public int getCustId()
  {
    return custId;
  }

  public Date getDateTime()
  {
    return datetime;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public double getMoviePrice()
  {
    return moviePrice;
  }

  public String toString()
  {
    return saleId + ", " + custId + ", " + datetime + ", " + movieId + ", " + moviePrice;
  }
}
