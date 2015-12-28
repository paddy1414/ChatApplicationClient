/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientCode;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author grahamm
 */
public interface ChatClientCallback extends Remote 
{
    public void displayNotification(String notification) throws RemoteException;
    public String getName() throws RemoteException;
}
