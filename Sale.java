// Sale.java by John Phillips on 10/3/2012 revised by Clark Hewitt 10/06/2012.
// This class represents a single sale from the digital movie store.

import java.util.*;

public class Sale
{
  int saleId;
  String datetime;
  int custId, totalItems;
  ArrayList<LineItem> lines;
  Double totalSale; 

  public Sale (int sid, String dt, int cid, ArrayList<LineItem> li, int ti, Double ts)
  {
    saleId = sid;
    datetime = dt;
    custId = cid;
    lines = li;
    totalItems = ti;
    totalSale = ts;
  }

  public int getSaleId()
  {
    return saleId;
  }

  public String getDateTime()
  {
    return datetime;
  }

  public int getCustId()
  {
    return custId;
  }

  public ArrayList<LineItem> getLines()
  {
    return lines;
  }

  public int getTotalItems()
  {
    return totalItems;
  }
 
  public Double getTotalSale()
  {
    return totalSale;
  } 

  public String toString()
  {
    return saleId + ", " + datetime + ", " + custId + ", " + totalItems + ", " + totalSale;
  }
}
