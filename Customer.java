// Customer.java by John Phillips revised by Clark Hewitt on 10/01/2012
// This class represents a single customer.

public class Customer
{
  int custId;
  String firstName;
  String lastName;
  String phone;


  public Customer(int cid, String fn, String ln, String p)
  {
    custId = cid;
    firstName = fn;
    lastName = ln;
    phone = p;

  }

  public int getCustId()
  {
    return custId;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPhone()
  {
    return phone;
  }

  public String toString()
  {
    return custId + ", " + firstName + ", " + lastName + ", " + phone;
  }
}
