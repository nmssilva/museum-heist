/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import auxiliary.VectorTimestamp;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nuno Silva
 */
public interface CSIAssaultThief  extends Remote{

    public VectorTimestamp amINeeded(int thiefID, int maxDisp, VectorTimestamp vt) throws RemoteException;
    
}
