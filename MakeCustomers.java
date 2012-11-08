// MakeCustomers.java by John Phillips on 9/4/2012
// This will create as many customer records as you desire for
// the store database example covered in class.

import java.io.*;
import java.util.*;

public class MakeCustomers
{
  public static void main(String[] args) throws Exception
  {
    final int MAX_RECORDS = 1000;
    final int CLASS_SIZE = 32;
    final String NEW_LINE = "\n";

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("cust.sql"))));

    String[] fnArray = {"Edward", "Dana", "Andrew", "Tyler", "Jonathan", "Wayne", "Robert",
      "Damion", "Derek", "Hollen", "Justin", "Thomas", "Clark", "Valerie",
      "Jordan", "Junxian", "Joshua", "Stephen",
      "Jonathan", "Dillon", "Jeffrey", "Andrew", "Carlos", "Keith", "Theresa", "Justin",
      "Bryan", "Tara", "William", "Jason", "Tyler", "River"};

    String[] lnArray = {"Austin", "Black", "Bortle", "Bowser", "Burnette", "Egli", "Faughnan",
      "Ferris", "Foulk", "Graver", "Hammond", "Hannon", "Hewitt", "Knapp", "Leeper",
      "Li", "Long", "Moore", "Moser", "Mulroy", "Nauroth", "Perry", "Rodriguez", "Sattazahn",
      "Seiders", "Walrath", "Ward", "Warriner", "Whitcomb", "Whitney", "Williams",
      "Phillips"};

    Random r = new Random();

    for(int i=1; i<=MAX_RECORDS; i++)
    {
      String s = "insert into customer values (null"
        + ", '" + fnArray[r.nextInt(CLASS_SIZE)]
        + "', '" + lnArray[r.nextInt(CLASS_SIZE)]
        + "', '" + r.nextInt(10) + r.nextInt(10) + r.nextInt(10)
        + "-" + r.nextInt(10) + r.nextInt(10) + r.nextInt(10)
        + "-" + r.nextInt(10) + r.nextInt(10) + r.nextInt(10) + r.nextInt(10)
        + "');" + NEW_LINE;

      out.print(s);
    }

    out.close();
  }
}
