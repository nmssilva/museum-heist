/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nuno Silva
 */
public interface CCSIMasterThief  extends Remote{

    public int appraiseSit(int nAssaultThievesCS) throws RemoteException;

    public void sendAssaultParty() throws RemoteException;

    public void takeARest() throws RemoteException;

    public void collectCanvas() throws RemoteException;

    public void sumUpResults() throws RemoteException;
    
}