/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MuseumHeist;

import AssaultParty.AssaultParty;
import GenRepOfInfo.GenRepOfInfo;
import Logger.Log;
import MasterThief.MasterThief;
import MasterThiefCtrlCollSite.MasterThiefCtrlCollSite;
import Museum.Museum;
import OrdThievesConcSite.OrdThievesConcSite;
import Thieves.Thief;

/**
 *
 * @author Nuno Silva
 * @author Pedro Coelho
 */

public class MuseumHeist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int   K = 3,      /* Número de elementos da assault party */
                    M = 7,      /* Número de ladrões incluindo ladroa chefe */
                    N = 5,      /* Número de rooms */
                    Q = 3;      /* Número de paintings */
        
        Log loging;
        Museum museum;  /* Museu */
        GenRepOfInfo genrepofinfo;  /* General Repository of Information */
        OrdThievesConcSite ordthievesconcsite;  /* Ordinary Thieves Concentration Site */
        MasterThiefCtrlCollSite mtctrlcollsite; /* Master Thief Control and Collection Site */
        
        MasterThief masterThief;    /* Ladroa chefe */
        AssaultParty[] assaultparties = new AssaultParty[(M-1)/K];   /* Array de assault parties */
        
        for( int i = 0; i < assaultparties.length ; i++){
            assaultparties[i] = new AssaultParty(K);
            for ( int j = 0; j < K ; j++){
                assaultparties[i].getParty()[j] = new Thief(j+(i*K));
            }
        }
        
    }
    
}
