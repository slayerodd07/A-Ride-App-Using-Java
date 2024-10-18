
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
// Simulation of a Simple Command-line based Uber App 

// This system supports "ride sharing" service and a delivery service

public class TMUberUI 
{
  public static void main(String[] args)
  {
    // Create the System Manager - the main system code is in here 

    TMUberSystemManager tmuber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())
    {
      String action = scanner.nextLine();

      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        return;
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        tmuber.listAllDrivers(); 
      }
      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        tmuber.listAllUsers(); 
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        tmuber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String carModel = "";
        System.out.print("Car Model: ");
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine();
        }
        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        String address ="";
        System.out.print("Address :");
        if(scanner.hasNextLine()){
          address=scanner.nextLine();
        }
        try{
          tmuber.registerNewDriver(name, carModel, license, address);
          System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s Address %-20s", name, carModel, license, address);
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
        
       // if (!tmuber.registerNewDriver(name, carModel, license))
         // System.out.println(tmuber.getErrorMessage()); 
       // else
          //System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s", name, carModel, license);
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        try{
          tmuber.registerNewUser(name, address, wallet);
          System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
        //if (!tmuber.registerNewUser(name, address, wallet))
          //System.out.println(tmuber.getErrorMessage());  
        //else
          //System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
      }
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestRide() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        String accountID="";
        String fromAddress="";
        String toAddress="";
        // I used scanner to read input from the user
        System.out.print("User Account Id: ");
        if(scanner.hasNextLine()){
        accountID=scanner.nextLine();
        }
        System.out.print("From Address: ");
        if(scanner.hasNextLine()){
        fromAddress=scanner.nextLine();
        }
        System.out.print("To Address: ");
        if(scanner.hasNextLine()){
        toAddress=scanner.nextLine();
        }
        try{
          String name=tmuber.getUser(accountID).getName();
          tmuber.requestRide(accountID, fromAddress, toAddress);
          System.out.println();
          System.out.printf("RIDE for: %-9s From: %-15s To: %-15s", name, fromAddress, toAddress);
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
        //if input is notvalid, error message will show up
        //if(!tmuber.requestRide(accountID, fromAddress, toAddress)){
          //System.out.println(tmuber.getErrorMessage());
        //}
        //else{
          // if the input is valid, the request is accepted and the request is printed
          //String name=tmuber.getUser(accountID).getName();
          //tmuber.requestRide(accountID, fromAddress, toAddress);
          //System.out.println();
          //System.out.printf("RIDE for: %-9s From: %-15s To: %-15s", name, fromAddress, toAddress);
        //}
      }
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestDelivery() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        // "Restaurant: "           (string)
        // "Food Order #: "         (string)
        String accountID="";
        String fromAddress="";
        String toAddress="";
        String Restaurant="";
        String FoodOrderID="";
        // I used scanner to take input from the user
        System.out.print("User Account Id: ");
        if(scanner.hasNextLine()){
        accountID=scanner.nextLine();
        }
        System.out.print("From Address: ");
        if(scanner.hasNextLine()){
        fromAddress=scanner.nextLine();
        }
        System.out.print("To Address: ");
        if(scanner.hasNextLine()){
        toAddress=scanner.nextLine();
        }
        System.out.print("Restaurant: ");
        if(scanner.hasNextLine()){
        Restaurant=scanner.nextLine();
        }
        System.out.print("Food Order #: ");
        if(scanner.hasNextLine()){
        FoodOrderID=scanner.nextLine();
        }
        // if the request is not valid, error message shows up
        //if(!tmuber.requestDelivery(accountID, fromAddress, toAddress, Restaurant, FoodOrderID)){
          //System.out.println(tmuber.getErrorMessage());
        //}
        try{
          // if the request is valid, the request is accepted and request is printed
          String name=tmuber.getUser(accountID).getName();
          tmuber.requestDelivery(accountID, fromAddress, toAddress, Restaurant, FoodOrderID);
          System.out.println();
          System.out.printf("DELIVERY for: %-9s From: %-15s To: %-15s", name, fromAddress, toAddress);
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
      }
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) 
      {
        tmuber.sortByUserName();
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) 
      {
        tmuber.sortByWallet();
      }
      // Sort current service requests (ride or delivery) by distance
      else if (action.equalsIgnoreCase("SORTBYDIST")) 
      {
        tmuber.sortByDistance();
      }
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) 
      {
        int zone=-1;
        int request = -1;
        System.out.print("Zone: ");
        if(scanner.hasNextInt()){
          zone=scanner.nextInt();
          scanner.nextLine();
        }
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        try{
          tmuber.cancelServiceRequest(zone, request);
          System.out.println("Zone :"+zone+" Service request #"+request+" cancelled");
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
        //if (!tmuber.cancelServiceRequest(request))
          //System.out.println(tmuber.getErrorMessage());  
        //else
          //System.out.println("Service request #" + request + " cancelled");
      }
      // Drop-off the user or the food delivery to the destination address
      else if (action.equalsIgnoreCase("DROPOFF")) 
      {
        String id="";
        System.out.print("Driver ID: ");
        if (scanner.hasNextLine())
        {
          id = scanner.nextLine();
        }
        try{
          tmuber.dropOff(id);
          System.out.println("Driver "+id+" Dropping Off");
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        } 
      }
        //if (!tmuber.dropOff(request))
          //System.out.println(tmuber.getErrorMessage());  
        //else
          //System.out.println("Successful Drop Off - Service request #" + request + " complete");
      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) 
      {
        System.out.println("Total Revenue: " + tmuber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
          System.out.println("\nValid Address"); 
        else
          System.out.println("\nBad Address"); 
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) 
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      else if (action.equalsIgnoreCase("PICKUP")){
        System.out.print("Driver ID: ");
        String id="";
        int zone=-1;
        if(scanner.hasNextLine()){
        id=scanner.nextLine();}
        try{
          Driver driver=tmuber.getDriver(id);
          tmuber.pickUp(id);
          zone=driver.getZone();
          System.out.println("Driver "+id+" Picking Up in Zone "+zone);
        }//catch(DriverNotFoundException e){
          //System.out.println(e.getMessage());
        //}//catch(DrivingException e){
          //System.out.println("Driver is on another service");
        //}//catch(NoRequestFound e){
          //Driver driver=tmuber.getDriver(id);
          //zone=driver.getZone();
          //System.out.println("No Service Request in Zone "+zone);
        //}
        catch(RuntimeException e){
          System.out.println(e.getMessage());
        }
      }
      else if(action.equalsIgnoreCase("LOADUSERS")){
        System.out.print("User File :");
        String filename="";
        if(scanner.hasNextLine()){
        filename=scanner.nextLine();}
        try{
          ArrayList<User> userList=TMUberRegistered.loadPreregisteredUsers(filename);
          tmuber.setUsers(userList);
          System.out.println("users loaded");
          tmuber.setUserList(userList);
        }catch(IOException e){
          if(e instanceof FileNotFoundException){
          System.out.println("Users File :"+filename+" Not Found");
          }
          else{
            System.out.println("Error");
            System.exit(1);
          }
        }
        
      }
      else if(action.equalsIgnoreCase("LOADDRIVERS")){
        System.out.print("Driver File :");
        String filename="";
        if(scanner.hasNextLine()){
        filename=scanner.nextLine();}
        try{
          ArrayList<Driver> driverList=TMUberRegistered.loadPreregisteredDrivers(filename);
          tmuber.setDrivers(driverList);
          System.out.println("Drivers Loaded"); 
        }catch(IOException e){
          if(e instanceof FileNotFoundException){
          System.out.println("Users File :"+filename+" Not Found");
          }
          else{
            System.out.println("Error");
            System.exit(1);
          }
        }  
             
      }
      else if(action.equalsIgnoreCase("DRIVETO")){
        String id="";
        String address="";
        System.out.print("Driver ID: ");
        if(scanner.hasNextLine()){
          id=scanner.nextLine();
        }
        System.out.println("Address: ");
        if(scanner.hasNextLine()){
          address=scanner.nextLine();
        }
        try{
          tmuber.driveTo(id, address);
        }catch(RuntimeException e){
          System.out.println(e.getMessage());
        } 
      }
      System.out.print("\n>");
    }
  }
}



