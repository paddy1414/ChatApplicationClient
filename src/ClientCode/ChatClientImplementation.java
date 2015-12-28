/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientCode;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author grahamm
 */
public class ChatClientImplementation extends UnicastRemoteObject 
                                            implements ChatClientCallback
{
    private String firstName;
   
    
    public ChatClientImplementation(String firstName) throws RemoteException
    {
        super();
        this.firstName = firstName;
     
    }

    public String getName() {
        return firstName;
    }
    
    public void displayNotification(String notification) throws RemoteException
    {
        System.out.println("Message from ChatServer: " + notification);
    }
}
