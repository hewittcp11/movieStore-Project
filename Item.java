// Item.java by Clark Hewitt on 9/26/2012
// This class represents a single item in a store.

public class Item
{
  int itemId;
  String itemName;
  int qtyInStock;
  Double retailPrice;
  Double wholesalePrice;

  public Item(int iid, String in, int qis, double rp, double wp)
  {
    itemId = iid;
    itemName = in;
    qtyInStock = qis;
    retailPrice = rp;
    wholesalePrice = wp;
  }

  public int getItemId()
  {
    return itemId;
  }

  public String getItemName()
  {
    return itemName;
  }

  public int getQtyInStock()
  {
    return qtyInStock;
  }

  public double getRetailPrice()
  {
    return retailPrice;
  }

  public double getWholesalePrice()
  {
    return wholesalePrice;
  }

  public String toString()
  {
    return itemId + ", " + itemName + ", " + qtyInStock + ", " + retailPrice  + ", " + wholesalePrice;
  }
}
