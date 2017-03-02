/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thieves;

import Logger.Log;

/**
 *
 * @author Pedro Coelho
 * @author Nuno Silva
 */
public class Thief extends Thread {

    private ThievesState state;
    private final int id;
    //private Room Room;
    private Log log;

    public Thief(ThievesState state, int id, Log log) {
        this.state = ThievesState.OUTSIDE;
        this.id = id;
        this.log = log;
    }

    private void handACanvas() {
        this.state = ThievesState.OUTSIDE;
    }

    private void amINeeded() {
        this.state = ThievesState.OUTSIDE;
    }

    private void prepareExcursion() {
        this.state = ThievesState.CRAWLING_INWARDS;
    }

    private void crawlIn() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    private void rollACanvas() {
        this.state = ThievesState.AT_A_ROOM;

    }

    private void reverseDirection() {
        this.state = ThievesState.CRAWLING_OUTWARDS;
    }

    private void crawlOut() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
