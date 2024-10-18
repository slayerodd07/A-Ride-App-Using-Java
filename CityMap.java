//Name: Sheikh Bidito Haque
//Student ID : 501220222
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// The city consists of a grid of 9 X 9 City Blocks

// Streets are east-west (1st street to 9th street)
// Avenues are north-south (1st avenue to 9th avenue)

// Example 1 of Interpreting an address:  "34 4th Street"
// A valid address *always* has 3 parts.
// Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
// Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
// Part 3: Must be "Street" or "Avenue" (case insensitive)

// Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
// For distance calculation you need to identify the the specific city block - in this example 
// it is city block (3, 4) (3rd avenue and 4th street)

// Example 2 of Interpreting an address:  "51 7th Avenue"
// Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
// For distance calculation you need to identify the the specific city block - 
// in this example it is city block (7, 5) (7th avenue and 5th street)
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{
  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s)
  {
    for (int i = 0; i < s.length(); i++)
      if (!Character.isDigit(s.charAt(i)))
        return false;
    return  true;
  }

  // Get all parts of address string
  // An easier solution would use String method split()
  // Other solutions are possible - you may replace this code if you wish
  private static String[] getParts(String address)
  {
    String parts[] = new String[3];
    
    if (address == null || address.length() == 0)
    {
      parts = new String[0];
      return parts;
    }
    int numParts = 0;
    Scanner sc = new Scanner(address);
    while (sc.hasNext())
    {
      if (numParts >= 3)
        parts = Arrays.copyOf(parts, parts.length+1);

      parts[numParts] = sc.next();
      numParts++;
    }
    if (numParts == 1)
      parts = Arrays.copyOf(parts, 1);
    else if (numParts == 2)
      parts = Arrays.copyOf(parts, 2);
    return parts;
  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    // Fill in the code
    // Make use of the helper methods above if you wish
    // There are quite a few error conditions to check for 
    // e.g. number of parts != 3
    //get the list of parts of an adddress
    String[] addressParts=getParts(address);
    //addressBlock is used to check the valid address
    String[] addressBlock={"1st","2nd","3rd","4th","5th","6th","7th","8th","9th"};
    //a flag is set here
    boolean wrongBlock=true;
    //check if address has all 3 parts
    if(addressParts.length!=3){return false;}
    else{
      String part1=addressParts[0];
      String part2=addressParts[1];
      String part3=addressParts[2];
      //check the validity of first part of address
      if(part1.length()!=2 || allDigits(part1)==false)
      {
        return false;
      }
      //if 1st part is correct, proceed to check the middle part of address
      else if(part2.length()!=3){return false;}
      else if(part2.length()==3){
        for(String block:addressBlock){
          if(part2.equals(block)){wrongBlock=false;}
          else{continue;}
        }
        if(wrongBlock==true){return false;}
        //if first 2 parts are correct,check the last part of the address
        if(wrongBlock==false){
          if(!part3.toLowerCase().equals("street")&&!part3.toLowerCase().equals("avenue")){return false;}
        }
      }
    }
    return true;
  }
  
  // Computes the city block coordinates from an address string
  // returns an int array of size 2. e.g. [3, 4] 
  // where 3 is the avenue and 4 the street
  // See comments at the top for a more detailed explanation
  public static int[] getCityBlock(String address)
  {
    int[] block = {-1, -1};
    if(validAddress(address)==false){
      return null;
  }
  //if address is not valid, null is returned
  //else the street and avenue number is casted as the description mentions
  else{
    String[] addressParts=getParts(address);
    String part1=addressParts[0];
    String part2=addressParts[1];
    String part3=addressParts[2];
    if(part3.toLowerCase().equals("avenue")){
      block[1]=chartoInt(part1.charAt(0));
      block[0]=chartoInt(part2.charAt(0));
    }   
    else{
      block[0]=chartoInt(part1.charAt(0));
      block[1]=chartoInt(part2.charAt(0));
    } 
  }
    return block;
  }
  
  // Calculates the distance in city blocks between the 'from' address and 'to' address
  // Hint: be careful not to generate negative distances
  
  // This skeleton version generates a random distance
  // If you do not want to attempt this method, you may use this default code
  public static int getDistance(String from, String to)
  {
    // Fill in the code or use this default code below. If you use
    // the default code then you are not eligible for any marks for this part
    if(validAddress(from)==false || validAddress(to)==false){
      return 0;
    }
    //if any of the address is not correct, it returns 0
    int[] blockFrom=getCityBlock(from);
    int[] blockTo=getCityBlock(to);
    //Absolute value is applied here to always get a positive distance
    int avenueDistance=Math.abs(blockFrom[0]-blockTo[0]);
    int streetDistance=Math.abs(blockFrom[1]-blockTo[1]);
    return Math.abs(avenueDistance+streetDistance);
  }
  //Created a private method to convert the avenue and street numbers to integer
  private static int chartoInt(Character a){
    if(a.equals('1')){return 1;}
    if(a.equals('2')){return 2;}
    if(a.equals('3')){return 3;}
    if(a.equals('4')){return 4;}
    if(a.equals('5')){return 5;}
    if(a.equals('6')){return 6;}
    if(a.equals('7')){return 7;}
    if(a.equals('8')){return 8;}
    if(a.equals('9')){return 9;}
    return 0;
  }
  public static int getCityZone(String address){
    if(!validAddress(address)){
      return -1;
    }
    else{
      int[] block=getCityBlock(address);
      int avenue=block[0];
      int street=block[1];
      if(0<avenue & avenue<6){
        if(5<street & street<10){
          return 0;
        }
        else{
          return 3;
        }
      }
      else{
        if(5<street & street<10){
          return 1;}
        else{
          return 2;
        }       
      }
    }
  }
}
