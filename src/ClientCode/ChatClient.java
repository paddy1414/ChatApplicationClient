/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientCode;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author grahamm
 */
public class ChatClient {

      public static void main(String[] args)
    {
        try
        {
            int RMIPort;
            String hostName;

            String userName = null;

            Scanner input = new Scanner(System.in);
            // Get the information for the registry
            System.out.println("Enter the RMIRegistry host name:");
          //  hostName = input.nextLine();
             hostName = "localhost";
            System.out.println("Enter the RMIregistry port number:");
          //  RMIPort = input.nextInt();
            RMIPort = 9112;
          //  input.nextLine();

            // find the remote object and cast it to an interface object
            String registryURL = "rmi://" + hostName + ":" + RMIPort + "/chatApp";
            ServerServiceCode.UserCommandsInInterface userObj = (ServerServiceCode.UserCommandsInInterface) Naming.lookup(registryURL);
            System.out.println("Lookup completed ");

            // Start logic of your client
            boolean exit = false;
            boolean loggedIn = false;
            System.out.println("Welcome to the MG Banking Application");
            while (!exit)
            {
                System.out.println("1) Register" );
                System.out.println("2) Log in to your account");
                System.out.println("3) Exit");
                String choice = input.nextLine();
              
                // Allow user to skip through log in step by making this an if instead of an else if
                  if (choice.equals("1")) {
                      System.out.println("**************Registration************");
                    System.out.println("Please enter your first name:");
                    String fName = input.nextLine();
                    System.out.println("Please enter your second name:");
                    String sName = input.nextLine();
                    System.out.println("Please enter your password:");
                    String pass = input.nextLine();
                    System.out.println("Please enter your userName:");
                    String email = input.nextLine();
                    System.out.println("**************************************");
                    boolean verdict = userObj.register(fName, sName, email, pass);
                    if (verdict)
                    {
                        System.out.println("Congratulations, you have registered successfully!");
                        // Move the user into the log in and use bank account menu
                        choice = "2";
                        // Set the user to be logged in & store their name information
                        loggedIn = true;
                        userName = fName;
                        
                    } else
                    {
                        System.out.println("Sorry, that user already exists. Please log in or register a new account.");
                    }
                  }
                if (choice.equals("2"))
                {
                    
                    while (!loggedIn)
                    {
                        System.out.println("***************Logging In**************");
                        System.out.println("Please log in to proceed");
                        System.out.println("Please enter your u name");
                        String uName = input.nextLine();
                        System.out.println("Please enter your password");
                        String pass = input.nextLine();
                       
                        System.out.println("*************************************");

                        // call remote method
                        boolean verdict = userObj.login(uName,  pass);
                        if (verdict)
                        {
                            // If the user was logged in successfully, set them to be logged in 
                            // and store their name information 
                            loggedIn = true;
                            userName = uName;
                        } else
                        {
                            System.out.println("Sorry, that login information was not found. Please try again.");
                        }
                    }

                    // Start menu functionality.
                    System.out.println("Welcome to the chat Bank, " + userName + ".");
                    // Register for callback with the server
                   ChatClientImplementation clientCallbackObj = new ChatClientImplementation(userName);
                    userObj.registerForCallbacks(clientCallbackObj);

                    // Continue with client code as normal
                    String menuChoice = "";
                    
                     while (!menuChoice.equals("5"))
                    {
                        System.out.println("What would you like to do?");
                        System.out.println("1) To join the group chat");
                        System.out.println("5) Exit the bank application.");

                        menuChoice = input.nextLine();
                        if(menuChoice.equals("1")) {
                            
                        }
                         if (menuChoice.equals("5"))
                        {
                            exit = true;
                            // Client has requested shutdown, unregister for callback
                            userObj.unregisterForCallbacks(clientCallbackObj);

                            // Make sure to TURN OFF the client object so that the client program can be turned off
                            UnicastRemoteObject.unexportObject(clientCallbackObj, true);
                        } else
                        {
                            System.out.println("Please choose a valid option from the menu listed.");
                        }
                        System.out.println("");
                    }
                    
                } else if (choice.equals("3"))
                {
                    exit = true;
                } else
                {
                    System.out.println("Please enter a selection from the menu options listed.");
                }
            }
            System.out.println("Thank you for using the Chat Application.");
            System.out.println("Program concluded.");

        } catch (Exception e)
        {
            System.out.println("Exception in Client: " + e);
        } // end catch
    }

}
