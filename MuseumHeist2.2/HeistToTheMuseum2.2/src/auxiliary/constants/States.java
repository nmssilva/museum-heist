/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary.constants;

/**
 *
 * @author Nuno Silva 72708, Pedro Coelho 59517
 */
public class States {

    // Status of AssaultThief
    public final static int /**
             * Status OUTSIDE.
             */
            OUTSIDE = 1000,
            /**
             * Status WAITING_SEND_ASSAULT_PARTY.
             */
            WAITING_SEND_ASSAULT_PARTY = 1001,
            /**
             * Status CRAWLING_INWARDS.
             */
            CRAWLING_INWARDS = 2000,
            /**
             * Status AT_A_ROOM.
             */
            AT_A_ROOM = 3000,
            /**
             * Status CRAWLING_OUTWARDS.
             */
            CRAWLING_OUTWARDS = 4000,
            /**
             * Status AT_COLLECTION_SITE.
             */
            AT_COLLECTION_SITE = 5000,
            /**
             * Status HEIST_END.
             */
            HEIST_END = 6000;

    // Status of MasterThief
    public final static int /**
             * Status PLANNING_THE_HEIST.
             */
            PLANNING_THE_HEIST = 1000,
            /**
             * Status DECIDING_WHAT_TO_DO.
             */
            DECIDING_WHAT_TO_DO = 2000,
            /**
             * Status ASSEMBLING_A_GROUP.
             */
            ASSEMBLING_A_GROUP = 3000,
            /**
             * Status WAITING_FOR_ARRIVAL.
             */
            WAITING_FOR_ARRIVAL = 4000,
            /**
             * Status PRESENTING_REPORT.
             */
            PRESENTING_REPORT = 5000;

}
