/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerServiceCode;

import ClientCode.ChatClientInterface;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author grahamm
 */
public interface UserCommandsInInterface extends Remote {
    /*
     1) Adding an account
     2) Logging in to an account
     3) Withdraw money from an account
     4) Deposit money into an account
     5) Get the balance within an account
     6) Calculate the interest to be earned by the balance
     7) Change the interest rate for all accounts    
     */

    public boolean register(String firstName, String lastName, String address, String password) throws RemoteException;

    public boolean login(String firstName,  String password) throws RemoteException;

    public boolean sendPmMail(String from, String to, String body, String subject) throws RemoteException;
   // public ArrayList<ChatMessage> getPmSentEmails(String sender) throws RemoteException;
 //   public ArrayList<ChatMessage> getPmReceivedEmails(String receiver) throws RemoteException;
    // CALLBACK METHODS

    public boolean registerForCallbacks(ChatClientInterface newClient) throws RemoteException;

    public boolean unregisterForCallbacks(ChatClientInterface exitingClient) throws RemoteException;
}
