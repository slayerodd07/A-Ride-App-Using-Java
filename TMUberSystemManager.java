
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Map;
/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  private TreeMap<String,User> users;
  private ArrayList<Driver> drivers;
  private ArrayList<User> userList;
  private Queue<TMUberService>[] serviceRequests; 

  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;
  public TMUberSystemManager() 
  {
    users=new TreeMap<>();
    drivers=new ArrayList<>();
    serviceRequests=new Queue[4];
    for(int i=0;i<4;i++){
      serviceRequests[i]=new LinkedList<TMUberService>();
    }
    totalRevenue=0;
    //users   = TMUberRegistered.loadPreregisteredUsers(users);
    //drivers = TMUberRegistered.loadPreregisteredDrivers(drivers);
    //serviceRequests = new ArrayList<TMUberService>(); 
    
    //TMUberRegistered.loadPreregisteredUsers(users);
    //TMUberRegistered.loadPreregisteredDrivers(drivers);
    userList=new ArrayList<>();
    for(User a:users.values()){
      userList.add(a);
    }
    //totalRevenue = 0;
  }
  public void setUserList(ArrayList<User> list){
    userList=list;
  }

  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  String errMsg = null;

  public String getErrorMessage()
  {
    return errMsg;
  }
  
  // Given user account id, find user in list of users
  // Return null if not found
  public void setUsers(ArrayList<User> list){
    for(User a:list){
      users.put(a.getAccountId(), a);
    }
  }
  
  public void setDrivers(ArrayList<Driver> driverList){
    this.drivers=driverList;
  }
  public User getUser(String accountId)
  {
    // Fill in the code
    // I used for each loop to iterate over every users until accountId is matched
    for(User a:users.values()){
      if(a.getAccountId().equals(accountId)){
        return a;
      }
      else{
        continue;
      }
    }
    return null;
  }
  
  // Check for duplicate user
  private boolean userExists(User user)
  {
    // Fill in the code
    // created a flag here
    boolean match=false;
    for(User a:users.values()){
      // this equals method is defined in user class
      if(user.equals(a)){
        match=true;
      }
      continue;
    }
    // if user is found, then return true
    if(match==true){return true;}
    // if user is not found, then return false
    return false;
  }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
   // Fill in the code
   // a flag is set here
   boolean match=false;
   for(Driver a:drivers){
    // this equals method is defined in driver class
     if(driver.equals(a)){
       match=true;
     }
     continue;
   }
   //if(match==false){
    //throw new DriverNotFoundException("Driver is not found");
  //}
   // if driver is found,then return true
   if(match==true){return true;}
   // if driver is not found, then return false
   return false;
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(TMUberService req)
  {
    // Fill in the code
    //String address=req.getFrom();
    //int zone=CityMap.getCityZone(address);
    //boolean match=false;
    for(int i=0;i<4;i++){
      // this equals method is defined in TMUberService and its subclasses
      for(TMUberService a:serviceRequests[i]){
      if(a.equals(req)){
        return true;
      }
      else{
        continue;
      }
    }
    }
    return false;
    //if(match==true){
      //throw new RequestAlreadyExistsException("Request already exists in the system");
    //}
    
  }

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }

  // Go through all drivers and see if one is available
  // Choose the first available driver
  // Return null if no available driver
  private Driver getAvailableDriver()
  {
    // Fill in the code
    for(Driver a:drivers){
      // status is called from the enum 
      // check if driver is available or driving
      if (a.getStatus()==Driver.Status.AVAILABLE){
        return a;
      }
      continue;
    }
    return null;
  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers()
  {
    System.out.println();
    
    //for (int i = 0; i < users.size(); i++)
    //{
      //int index = i + 1;
      //System.out.printf("%-2s. ", index);
      //users.get(i).printInfo();
      //System.out.println(); 
    //}
    int index=0;
    for(User a:users.values()){
      index++;
      System.out.printf("%-2s. ", index);
      a.printInfo();
      System.out.println();
    }
  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    System.out.println();
    
    for (int i = 0; i < drivers.size(); i++)
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      // use the printInfo() method defined in driver class
      drivers.get(i).printInfo();
      System.out.println(); 
    }
    // Fill in the code
  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    System.out.println();
    
    for (int i = 0; i < serviceRequests.length; i++)
    {
      //int index = i + 1;
      //System.out.printf("%-2s. %s", index,"-".repeat(50));
      // use the printInfo() method defined in TMUberService and it subclasses
      //serviceRequests.get(i).printInfo();
      //System.out.println();
      int index=0;
      System.out.println("ZONE "+i);
      System.out.println("======");
      System.out.println();
      for(TMUberService a:serviceRequests[i]){
        index=index+1;
        System.out.printf("%-2s. %s", index,"-".repeat(50));
        a.printInfo();
        System.out.println();
      } 
    }
    // Fill in the code
  }

  // Add a new user to the system
  public void registerNewUser(String name, String address, double wallet)
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!

    //here errMsg is set as the error message to be displayed according to the word file given
    if(CityMap.validAddress(address)==false){
      //checked if the address is valid
      //errMsg="Invalid User Address";
      //return false;
      throw new InvalidAddressException("Invalid Address");
    }
    else if(name.trim().length()==0 || name.equals(null)){
      //checked if name is empty or null
      //errMsg="Invalid User Address";
      //return false;
      throw new InvalidUserException("Invalid User Name");
    }
    else if(wallet<0){
      //checked if wallet is valid or not
      throw new InvalidWalletException("Wallet must be 0 or more");
    }
    else{
      String id=TMUberRegistered.generateUserAccountId(userList);
      User a=new User(id, name, address, wallet);
      //checked if user is already in the system or not
      if(userExists(a)==true){
        throw new UserExistsException("User Already Exists in System");
        //return false;
      }
      else{
        //if everything is valid, the user is added to the system
        userList.add(a);
        users.put(id, a);
        //return true;
      }
    }

  }

  // Add a new driver to the system
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address)
  {
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser
    if(!CityMap.validAddress(address)){
      throw new InvalidAddressException("Invalid Address");
    }
    if(name.trim().length()==0 || name.equals(null)){
      //checked if name is empty or null
      //errMsg="Invalid Driver Name";
      //return false;
      throw new InvalidDriverNameException("Invalid Driver Name");
    }
    else if(carModel.trim().length()==0 || carModel.equals(null)){
      //checked if carmodel is empty or null
      //errMsg="Invalid Car Model";
      //return false;
      throw new InvalidCarModelException("Invalid Car Model");
    }
    else if(carLicencePlate.trim().length()==0 || carLicencePlate.equals(null)){
      //checked if carLicencePlate is empty or null
      //errMsg="Invalid Car Licence Plate";
      //return false;
      throw new InvalidCarLicensePlateException("Invalid Car Licence Plate");
    }
    else{
      String id=TMUberRegistered.generateDriverId(drivers);
      Driver a=new Driver(id, name, carModel, carLicencePlate,address);
      if(driverExists(a)==true){
        //driver is generated and checked if it already exists in the system or not
        //errMsg="Driver Already Exists in System";
        //return false;
        throw new DriverAlreadyExistsException("Driver Already Exists in System");
      }
    else{
    //String id=TMUberRegistered.generateDriverId(drivers);
    //Driver newDriver=new Driver(id, name, carModel, carLicencePlate,address);
    // if everything is valid, the generated driver is added to the system
    drivers.add(a);
    }
  }
    //return true;
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to)
  {
    // Check for valid parameters
	// Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user
    // check if the accountId is valid or not
    if(getUser(accountId)==null){
      throw new InvalidUserException("User Account Not Found");
      //return false;
    }
    User currentUser=getUser(accountId);
    TMUberRide currentRequest=new TMUberRide(from, to, currentUser, CityMap.getDistance(from, to), getRideCost(CityMap.getDistance(from, to)));
    //for(TMUberService a:serviceRequests){
      //if(a.getUser().equals(currentUser)){
        //if(a.getServiceType().equals("RIDE")){
          //errMsg="User Already Has Ride Request";
          //return false;
        //}
    if(existingRequest(currentRequest)){
      throw new UserHasRequestException("User Already Has Ride Request");
    }
      
    else if(CityMap.validAddress(from)==false || CityMap.validAddress(to)==false){
      //errMsg="Invalid Address";
      //return false;
      throw new InvalidAddressException("Invalid Address");
    }
    else if(CityMap.getDistance(from, to)<=1){
      //errMsg="Insufficient Travel Distance";
      //return false;
      throw new InsufficientTravelDistanceException("Insufficient Travel Distance");
    }
    else if(currentUser.getWallet()<getRideCost(CityMap.getDistance(from, to))){
      //errMsg="Insufficient Funds";
      //return false;
      throw new InsufficientFundsException("Insufficient Funds");
    }
   // else if(getAvailableDriver()==null){
      //errMsg=("No Drivers Available");
      //return false;
     // throw new DriverNotFoundException("No Drivers Available");
    //}
    else{
      //Driver availableDriver=getAvailableDriver();
      //availableDriver.setStatus(Driver.Status.DRIVING);
      //TMUberRide currentRequest=new TMUberRide(from, to, currentUser, CityMap.getDistance(from, to), getRideCost(CityMap.getDistance(from, to)));
      int zone=CityMap.getCityZone(from);
      serviceRequests[zone].add(currentRequest);
      currentUser.addRide();
      
    }
    
  }

  // Request a food delivery. User wallet will be reduced when drop off happens
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId)
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had
    User currentUser=null;
    if(getUser(accountId)==null){
      //check if the accountId is valid
      throw new InvalidUserException("User Account Not Found");
      //return false;
    }
    currentUser=getUser(accountId);
    // used CityMap class and its methods to verify the address and get the distance
    if(CityMap.validAddress(from)==false || CityMap.validAddress(to)==false){
      throw new InvalidAddressException("Invalid Address");
      //return false;
    }
    int distance=CityMap.getDistance(from, to);
    if(distance<=1){
      throw new InsufficientTravelDistanceException("Insufficient Travel Distance");
      //return false;
    }
    // check if restaurant name is empty or null
    if((restaurant.trim().length()==0)|| restaurant.equals(null)){
      throw new InvalidRestaurantException("Restaurant name is not valid");
      //return false;
    }
    // check if foodOrderId is empty or null
    if(foodOrderId.trim().length()==0||foodOrderId.equals(null)){
      throw new InvalidOrderIDException("Food Order ID is not valid");
      //return false;
    }
    // check if the user has enough money for the ride
    // used the getDistance() method defined earlier
    if(currentUser.getWallet()<getRideCost(CityMap.getDistance(from, to))){
      throw new InsufficientFundsException("Insufficient Funds");
      //return false;
    }
    // checked if any driver is available or not
    //if(getAvailableDriver()==null){
      //errMsg=("No Drivers Available");
      //return false;
    //}
    // check if the request already exists
    //Driver availableDriver=getAvailableDriver();
    TMUberDelivery currentRequest= new TMUberDelivery(from, to, currentUser, distance, getDeliveryCost(distance), restaurant, foodOrderId);
      if(existingRequest(currentRequest)==true){
        throw new UserHasRequestException("User Already Has Delivery Request at Restaurant with this Food Order");
        //return false;
      }
    // if everything is valid, the driver's status is set to driving and the request is added to the system
    //availableDriver.setStatus(Driver.Status.DRIVING);
    int zone=CityMap.getCityZone(from);
    serviceRequests[zone].add(currentRequest);
    // the number of deliveries for the user is increased
    currentUser.addDelivery();

    //return true;
  }


  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public void cancelServiceRequest(int zone,int request)
  {
    // Check if valid request #
    // Remove request from list
    // Also decrement number of rides or number of deliveries for this user
    // since this ride/delivery wasn't completed

    // check if the request number is valid
    // 1<=request #<= size of the arraylist serviceRequests
    // errMsg is set if the request number is out of bounds
    if(zone<0 || zone>3){
      throw new InvalidZoneException("Invalid Zone");
    }
    if(request>(serviceRequests[zone].size())||request<1){
      throw new InvalidRequestException("Invalid Request #");
      //return false;
    }
    // get the request to be cancelled from arraylist
    Queue<TMUberService> temp=new LinkedList<TMUberService>(serviceRequests[zone]);
    for(int i=0;i<request-1;i++){
      temp.poll();
    }
    TMUberService a=temp.peek();
    //TMUberService a=serviceRequests[zone].get(request-1);

    // get the specific user
    User user=a.getUser();
    // the number of delivery or ride of the user is decremented depending on the type of service cancelled
    if (a.getServiceType().equals("RIDE")){
      user.decrementRide();
    }
    else{
      user.decrementDelivery();
    }
    // get the driver for the request
    //Driver driver=a.getDriver();
    // driver is made available
    //driver.setStatus(Driver.Status.AVAILABLE);
    // the request is removed from the arraylist serviceRequests
    serviceRequests[zone].remove(a);
    //return true;
  }
  public Driver getDriver(String driverId){
    for(Driver d:drivers){
      if(d.getId().equals(driverId)){
        return d;
      }
    }
    return null;
  }
  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public void pickUp(String driverId){
    Driver d=getDriver(driverId);
    if(d==null){
      throw new DriverNotFoundException("Invalid Driver ID");
    }
    else if(d.getStatus()==Driver.Status.DRIVING){
      throw new DrivingException("Driver is already driving");
    }
    int zone=d.getZone();
    if(serviceRequests[zone].size()==0){
      throw new NoRequestFound("There is no request in the zone");
    }
    else{
    TMUberService a=serviceRequests[zone].poll();
    String from=a.getFrom();
    d.updateService(a);
    d.setAddress(from);
    d.setStatus(Driver.Status.DRIVING);}
  }
  public void dropOff(String driverId)
  {
    // See above method for guidance
    // Get the cost for the service and add to total revenues
    // Pay the driver
    // Deduct driver fee from total revenues
    // Change driver status
    // Deduct cost of service from user
    Driver driver=getDriver(driverId);
    if(driver==null){
      throw new DriverNotFoundException("Invalid Driver ID");
    }
    TMUberService a=driver.getService();
    if(a==null){
      throw new DropoffException("This driver has no dropoff");
    }
    //check if the request number is valid or not
    //the range of request number is from 1 to length of the arraylist serviceRequests
    //if(request>(serviceRequests.size())|| request<1){
      //errMsg="Invalid Request #";
      //return false;
    //}
    //get the specific service for dropoff
    //TMUberService a=serviceRequests.get(request-1);
    // get the driver for the service
    //Driver driver=a.getDriver();
    // get the user for the service
    else{
    User user=a.getUser();
    // get the cost for the service
    double cost=a.getCost();
    // cost is deducted from the wallet of user
    user.payForService(cost);
    // the revenue is increased
    totalRevenue=totalRevenue+cost;
    // driver is paid according to the rate
    double driverEarning=cost*PAYRATE;
    driver.pay(driverEarning);
    // deduct the driver payment from total revenue
    totalRevenue=totalRevenue-driverEarning;
    // driver is made available again
    driver.setAddress(a.getTo());
    driver.setStatus(Driver.Status.AVAILABLE);}
    // the request is removed from the arraylist of service requests
    //int index=serviceRequests.indexOf(a);
    //serviceRequests.remove(index);
    //return true;
  }
  public void driveTo(String driverId,String address){
    Driver driver=getDriver(driverId);
    if(driver==null){
      throw new DriverNotFoundException("Invalid Driver ID");
    }
    else if(!CityMap.validAddress(address)){
      throw new InvalidAddressException("Invalid Address");
    }
    else if(driver.getStatus()==Driver.Status.DRIVING){
      throw new DrivingException("Driver is not available");
    }
    else{
      driver.setAddress(address);
    }
  }

  // Sort users by name
  // Then list all users
  public void sortByUserName()
  {
    // the arraylist is sorted here using the helper class
   Collections.sort(userList, new NameComparator());
   users.clear();
   for(int i=0;i<userList.size();i++){
    User u=userList.get(i);
    String id=u.getAccountId();
    users.put(id, u);
   }
   listAllUsers(); 
  }

  // Helper class for method sortByUserName
  //interface is used here to compare
  private class NameComparator implements Comparator<User>
  {
    public int compare(User x,User y){
      return x.getName().compareTo(y.getName());
    }
  }

  // Sort users by number amount in wallet
  // Then list all users
  
  public void sortByWallet()
  {
    // the arraylist is sorted here using the helper class
    Collections.sort(userList, new UserWalletComparator());
    users.clear();
    for(int i=0;i<userList.size();i++){
     User u=userList.get(i);
     String id=u.getAccountId();
     users.put(id, u);
    }
    listAllUsers();
  }
  // Helper class for use by sortByWallet
  //interface is used here to compare
  private class UserWalletComparator implements Comparator<User>
  {
    public int compare(User x,User y){
      if(x.getWallet()<y.getWallet()){return -1;}
      if(x.getWallet()>y.getWallet()){return 1;}
      return 0;
    }
  }

  // Sort trips (rides or deliveries) by distance
  // Then list all current service requests
  public void sortByDistance()
  {
    //the arraylist is sorted here using the compareTo method defined in TMUberService
    for(Queue<TMUberService> a:serviceRequests){
    List<TMUberService> temp=new ArrayList<>(a);
    Collections.sort(temp);
    a.clear();
    a.addAll(temp);
    }
    listAllServiceRequests();
  }
}
class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(){}
    public DriverNotFoundException(String message){
      super(message);
    }
}
class RequestAlreadyExistsException extends RuntimeException{
  public RequestAlreadyExistsException(){};
  public RequestAlreadyExistsException(String message){
    super(message);
  }
}
class InvalidAddressException extends RuntimeException{
  public InvalidAddressException(){};
  public InvalidAddressException(String message){
    super(message);
  }
}
class InsufficientFundsException extends RuntimeException{
  public InsufficientFundsException(){};
  public InsufficientFundsException(String message){
    super(message);
  }
}
class DriverAlreadyExistsException extends RuntimeException{
  public DriverAlreadyExistsException(){};
  public DriverAlreadyExistsException(String message){
    super(message);
  }
} 
class IllegalUserNameException extends RuntimeException{
  public IllegalUserNameException(){};
  public IllegalUserNameException(String message){
    super(message);
  }
}
class InvalidMoneyException extends RuntimeException{
  public InvalidMoneyException(){};
  public InvalidMoneyException(String message){
    super(message);
  }
}
class UserExistsException extends RuntimeException{
  public UserExistsException(){};
  public UserExistsException(String m){
    super(m);
  }    
}
class InvalidDriverNameException extends RuntimeException{
  public InvalidDriverNameException(){};
  public InvalidDriverNameException(String m){
    super(m);
  }
}
class InvalidCarModelException extends RuntimeException{
  public InvalidCarModelException(){};
  public InvalidCarModelException(String m){
    super(m);
  }
}
class InvalidUserException extends RuntimeException{
  public InvalidUserException(){};
  public InvalidUserException(String m){
    super(m);
    }
}
class TravelDistanceException extends RuntimeException{
  public TravelDistanceException(){};
  public TravelDistanceException(String m){
    super(m);
  }
}
class InvalidWalletException extends RuntimeException{
  public InvalidWalletException(){};
  public InvalidWalletException(String m){
    super(m);
  }
}
class InvalidRequestException extends RuntimeException{
  public InvalidRequestException(){};
  public InvalidRequestException(String m){
    super(m);
  }
}
class UserHasRequestException extends RuntimeException{
  public UserHasRequestException(){};
  public UserHasRequestException(String m){
    super(m);
  }
}
class InvalidCarLicensePlateException extends RuntimeException{
  public InvalidCarLicensePlateException(){};
  public InvalidCarLicensePlateException(String m){
    super(m);
  }
}
class InvalidOrderIDException extends RuntimeException{
  public InvalidOrderIDException(){};
  public InvalidOrderIDException(String m){
    super(m);
  }
}
class InsufficientTravelDistanceException extends RuntimeException{
  public InsufficientTravelDistanceException(){};
  public InsufficientTravelDistanceException(String m){
    super(m);  
  }
}
class InvalidRestaurantException extends RuntimeException{
  public InvalidRestaurantException(){};
  public InvalidRestaurantException(String m){
    super(m);
  }
}
class DrivingException extends RuntimeException{
  public DrivingException(){};
  public DrivingException(String m){
    super(m);
  }
}
class NoRequestFound extends RuntimeException{
  public NoRequestFound(){};
  public NoRequestFound(String m){
    super(m);
}
}
class DropoffException extends RuntimeException{
  public DropoffException(){};
  public DropoffException(String m){
    super(m);
  }
}
class InvalidZoneException extends RuntimeException{
  public InvalidZoneException(){};
  public InvalidZoneException(String m){
    super(m);
  }
}