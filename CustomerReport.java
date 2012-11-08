// CustomerReport.java by Clark Hewitt on 10/23/2012
// This class represents a customer report showing when customers have made prchases.

import java.util.*;
import java.text.*;

public class CustomerReport
{
  int custId;
  String firstName;
  String lastName;
  Date datetime;

  public CustomerReport(int cid, String fn, String ln, Date dt)
  {
    custId = cid;
    firstName = fn;
    lastName = ln;
    datetime = dt;
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

  public Date getDateTime()
  {
      return datetime;
  }

  public String toString()
  {
    return custId + ", " + firstName + ", " + lastName + ", " + datetime;
  }
}
