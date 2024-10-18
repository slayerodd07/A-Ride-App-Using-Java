//Name: Sheikh Bidito Haque
//Student ID: 501220222
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TMUberRegistered
{
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;

    // Generate a new user account id
    public static String generateUserAccountId(ArrayList<User> current)
    {
        return "" + firstUserAccountID + current.size();
    }

    // Generate a new driver id
    public static String generateDriverId(ArrayList<Driver> current)
    {
        return "" + firstDriverId + current.size();
    }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    // The test scripts and test outputs included with the skeleton code use these
    // users and drivers below. You may want to work with these to test your code (i.e. check your output with the
    // sample output provided). 
    public static ArrayList<User> loadPreregisteredUsers(String filename) throws IOException
    {
        ArrayList<User> users=new ArrayList<>();
        File file=new File(filename);
        Scanner scanner=new Scanner(file);
        while (scanner.hasNextLine()) {
            String name=scanner.nextLine();
            String address=scanner.nextLine();
            double wallet=Double.parseDouble(scanner.nextLine());
            User a=new User(generateUserAccountId(users), name, address, wallet);
            users.add(a);
        }
        scanner.close();
        return users;
        //users.add(new User(generateUserAccountId(users), "McInerney, T.", "34 5th Street", 25));
        //users.add(new User(generateUserAccountId(users), "Valenzano, R.", "71 1st Street", 55));
        //users.add(new User(generateUserAccountId(users), "Lugez, E.", "55 9th Avenue", 125));
        //users.add(new User(generateUserAccountId(users), "Miranskyy, A.", "15 2nd Avenue", 15));
        //users.add(new User(generateUserAccountId(users), "Raman, P.", "32 3rd Street", 13));
        //users.add(new User(generateUserAccountId(users), "Woungang, I.", "64 6th Avenue", 27));
        //users.add(new User(generateUserAccountId(users), "Soutchanski, M.", "28 7th Avenue", 22));
        //users.add(new User(generateUserAccountId(users), "Harley, E.", "10 7th Avenue", 34));
        //users.add(new User(generateUserAccountId(users), "Mason, D.", "48 8th Street", 11));
        //users.add(new User(generateUserAccountId(users), "Santos, M.", "83 4th Street", 41));
    }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    public static ArrayList<Driver> loadPreregisteredDrivers(String filename) throws FileNotFoundException
    {
        ArrayList<Driver> drivers=new ArrayList<>();
        File a=new File(filename);
        Scanner scanner=new Scanner(a);
        while (scanner.hasNextLine()) {
            String name=scanner.nextLine();
            String carModel=scanner.nextLine();
            String licensePlate=scanner.nextLine();
            String address=scanner.nextLine();
            Driver d=new Driver(generateDriverId(drivers), name, carModel, licensePlate, address);
            drivers.add(d);
        }
        scanner.close();
        return drivers;
        //drivers.add(new Driver(generateDriverId(drivers), "Tom Cruise", "Toyota Corolla", "MAVERICK"));
        //drivers.add(new Driver(generateDriverId(drivers), "Brad Pitt",  "Audi S4", "FGDR 983"));
        //drivers.add(new Driver(generateDriverId(drivers), "Millie Brown",  "Tesla", "STRNGRTHGS"));
        //drivers.add(new Driver(generateDriverId(drivers), "Tim Chalamet",  "Thopter", "DUNE"));
        //drivers.add(new Driver(generateDriverId(drivers), "John Boyega",  "X-Wing", "REBEL"));
    }
}

