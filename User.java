//Name: Sheikh Bidito Haque
//Student ID: 501220222
/*
 * 
 * Class that simulates a user of a simple Uber app
 * 
 * Everything has been done for you except equals() method
 */


public class User 
{
  private String accountId;  
  private String name;
  private String address;
  private double wallet; // load up with money
  private int rides;
  private int deliveries;
  
  public User(String id, String name, String address, double wallet)
  {
    this.accountId = id;
    this.name = name;
    this.address = address;
    this.wallet = wallet;
    this.rides = 0;
    this.deliveries = 0;
  } 

  // Getters and Setters
  public String getAccountId()
  {
    return accountId;
  }
  public void setAccountId(String accountId)
  {
    this.accountId = accountId;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getAddress()
  {
    return address;
  }
  public void setAddress(String address)
  {
    this.address = address;
  }
  public double getWallet()
  {
    return wallet;
  }
  public void setWallet(int wallet)
  {
    this.wallet = wallet;
  }
  public int getRides()
  {
    return rides;
  }
  public void addRide()
  {
    this.rides++;
  }
  public void addDelivery()
  {
    this.deliveries++;
  }
  public int getDeliveries()
  {
    return deliveries;
  }
  // the following two methods are created to decrement number of deliveries and rides for the user
  public void decrementDelivery()
  {
    this.deliveries=this.deliveries-1;
  }
  public void decrementRide()
  {
    this.rides=this.rides-1;
  }
  
  // Pay for the cost of the service
  // This method assumes that there are sufficient funds in the wallet
  public void payForService(double cost)
  {
    wallet -= cost;
  }
  // Print Information about a User  
  public void printInfo()
  {
    System.out.printf("Id: %-5s Name: %-15s Address: %-15s Wallet: %2.2f", accountId, name, address, wallet);
  }
  
  /*
   * Two users are equal if they have the same name and address.
   * This method is overriding the inherited method in superclass Object
   * 
   * Fill in the code
   */
  public boolean equals(Object other)
  {
    //cast the object to a user
    User a=(User) other;
    //check the conditions for it to be equal
    if(this.name.equals(a.name) && this.address.equals(a.address)){return true;}
    return false;
  }
}
