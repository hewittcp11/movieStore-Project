// LineItem.java by John Phillips on 10/3/2012 revised by Clark Hewitt 10/06/2012.
// This class represents a single line item sold in a movieStore sale.

public class LineItem
{
  int saleId;
  int movieId;
  int qty;
  double moviePrice;

  public LineItem (int sid, int i, int q, double p)
  {
    saleId = sid;
    movieId = i;
    qty = q;
    moviePrice = p;
  }

  public int getSaleId()
  {
    return saleId;
  }

  public int getMovieId()
  {
    return movieId;
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
    return saleId + ", " + movieId + ", " + qty + ", " + moviePrice;
  }
}
