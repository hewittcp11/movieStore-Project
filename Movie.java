// Movie.java by Clark Hewitt on 10/16/2012
// This class represents a single digital movie in the digital movie store, movieStore.

public class Movie
{
  int movieId;
  String movieName;
  String movieYear;
  Double moviePrice;
  String movieSize;

  public Movie(int mid, String mn, String my, double mp, String ms)
  {
    movieId = mid;
    movieName = mn;
    movieYear = my;
    moviePrice = mp;
    movieSize = ms;
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

  public double getMoviePrice()
  {
    return moviePrice;
  }

  public String getMovieSize()
  {
    return movieSize;
  }

  public String toString()
  {
    return movieId + ", " + movieName + ", " + movieYear + ", "
    + moviePrice  + ", " + movieSize;
  }
}
