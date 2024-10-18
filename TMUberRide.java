//Name: Sheikh Bidito Haque
//Student ID: 501220222
/*
 * 
 * This class simulates an ride service for a simple Uber app
 * 
 * A TMUberRide is-a TMUberService with some extra functionality
 */
public class TMUberRide extends TMUberService
{
  private int numPassengers;
  private boolean requestedXL;
  
  public static final String TYPENAME = "RIDE";
  
  // Constructor to initialize all inherited and new instance variables 
  public TMUberRide(String from, String to, User user, int distance, double cost)
  {
    // Fill in the code - make use of the super method
    //super method is used to get the constructors from superclass TMUberService
    super(from, to, user, distance, cost, TYPENAME);
    //initially the numpassengers is set to 0 and requestedXL is set to be false
    this.numPassengers=0;
    this.requestedXL=false;
  }
  
  public String getServiceType()
  {
    return TYPENAME;
  }

  public int getNumPassengers()
  {
    return numPassengers;
  }

  public void setNumPassengers(int numPassengers)
  {
    this.numPassengers = numPassengers;
  }

  public boolean isRequestedXL()
  {
    return requestedXL;
  }

  public void setRequestedXL(boolean requestedXL)
  {
    this.requestedXL = requestedXL;
  }
}
