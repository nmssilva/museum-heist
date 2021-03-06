package monitors;

import entities.AssaultThief;
import entities.MasterThief;
import interfaces.ILogger;
import genclass.*;
import static auxiliary.Heist.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nuno Silva 72708, Pedro Coelho 59517
 */
public class Logger implements ILogger {

    private String fileName;
    private String masterThiefStatus;
    private String[] assaultThiefStatus;
    private int[] assaultThiefMaxDisp;
    private String[] assaultThiefSituation;
    private int[] hasCanvas;
    private int nPaintings;
    private Room[] rooms;
    private TextFile log;
    private int[][] asspartiesPos;
    private int[][] assparties;
    private int[] asspartiesRoomID;
    private String lastLine, lastLine2;

    /**
     *
     */
    public Logger() {
        assaultThiefStatus = new String[THIEVES_NUMBER];
        assaultThiefMaxDisp = new int[THIEVES_NUMBER];
        assaultThiefSituation = new String[THIEVES_NUMBER];
        hasCanvas = new int[THIEVES_NUMBER];
        nPaintings = 0;
        rooms = new Room[ROOMS_NUMBER];
        lastLine = "";
        lastLine2 = "";

        assparties = new int[2][MAX_ASSAULT_PARTY_THIEVES];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < MAX_ASSAULT_PARTY_THIEVES; j++) {
                assparties[i][j] = -1;
            }
        }

        asspartiesPos = new int[2][MAX_ASSAULT_PARTY_THIEVES];

        asspartiesRoomID = new int[2];
        for (int i = 0; i < 2; i++) {
            asspartiesRoomID[i] = -1;
        }

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
        fileName = "Heistothemuseum_" + date.format(today) + ".log";

        this.log = new TextFile();
    }

    /**
     * Set the number of Paintings in the General Repository of Information.
     *
     * @param nPaintings
     */
    @Override
    public void setnPaintings(int nPaintings) {
        this.nPaintings = nPaintings;
    }

    /**
     * Set an Assault Party in the General Repository of Information.
     *
     * @param id
     * @param elements
     * @param positions
     * @param roomID
     */
    @Override
    public void setAssaultParty(int id, int[] elements, int[] positions, int roomID) {
        assparties[id] = elements;
        asspartiesPos[id] = positions;
        asspartiesRoomID[id] = roomID;
    }

    /**
     * Set the Master Thief State in the Logger
     *
     */
    @Override
    public void setMasterState() {
        MasterThief mthief = (MasterThief) Thread.currentThread();
        int status = mthief.getStatus();
        switch (status) {
            case 1000:
                masterThiefStatus = "PLAN";
                break;
            case 2000:
                masterThiefStatus = "DCID";
                break;
            case 3000:
                masterThiefStatus = "ASSG";
                break;
            case 4000:
                masterThiefStatus = "WAIT";
                break;
            case 5000:
                masterThiefStatus = "PRES";
                break;

        }
    }

    /**
     * Set an Assault Thief State in the Logger
     *
     */
    @Override
    public void setAssaultThief() {
        AssaultThief thief = (AssaultThief) Thread.currentThread();
        int status = thief.getStatus();
        switch (status) {
            case 1000:
                assaultThiefStatus[thief.getThiefID()] = "OUTS";
                break;
            case 1001:
                assaultThiefStatus[thief.getThiefID()] = "WAIT";
                break;
            case 2000:
                assaultThiefStatus[thief.getThiefID()] = "CR_I";
                break;
            case 3000:
                assaultThiefStatus[thief.getThiefID()] = "ROOM";
                break;
            case 4000:
                assaultThiefStatus[thief.getThiefID()] = "CR_O";
                break;
            case 5000:
                assaultThiefStatus[thief.getThiefID()] = "COLL";
                break;
            case 6000:
                assaultThiefStatus[thief.getThiefID()] = "HEND";
                break;

        }
        assaultThiefMaxDisp[thief.getThiefID()] = thief.getMaxDisp();
        if (thief.getPartyID() == -1) {
            assaultThiefSituation[thief.getThiefID()] = "W";
        } else {
            assaultThiefSituation[thief.getThiefID()] = "" + thief.getPartyID();
        }
        hasCanvas[thief.getThiefID()] = thief.getHasCanvas();
    }

    /**
     * Set the Museum in the General Repository of Information.
     *
     * @param rooms
     */
    @Override
    public void setMuseum(Room[] rooms) {
        this.rooms = rooms;
    }

    /**
     * Log the initial status of the Heist.
     *
     */
    @Override
    public void reportInitialStatus() {
        if (!log.openForWriting(".", fileName)) {
            GenericIO.writelnString("2 A operação de criação do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
        log.writelnString("                             Heist to the Museum - Description of the internal state\n\n");
        log.writelnString("MstT   Thief 1      Thief 2      Thief 3      Thief 4      Thief 5      Thief 6");
        log.writelnString("Stat  Stat S MD    Stat S MD    Stat S MD    Stat S MD    Stat S MD    Stat S MD");
        log.writelnString("                   Assault party 1                       Assault party 2                       Museum");
        log.writelnString("           Elem 1     Elem 2     Elem 3          Elem 1     Elem 2     Elem 3   Room 1  Room 2  Room 3  Room 4  Room 5");
        log.writelnString("    RId  Id Pos Cv  Id Pos Cv  Id Pos Cv  RId  Id Pos Cv  Id Pos Cv  Id Pos Cv   NP DT   NP DT   NP DT   NP DT   NP DT");
        if (!log.close()) {
            GenericIO.writelnString("A operação de fecho do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
    }

    /**
     * Log the status of everything in the General Repository of Information.
     *
     */
    @Override
    public synchronized void reportStatus() {
        boolean dontPrint = false;
        if (!log.openForAppending(".", fileName)) {
            GenericIO.writelnString("3 A operação de criação do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }

        String line = "";
        String line2 = "";

        line += masterThiefStatus + "  ";
        for (int i = 0; i < THIEVES_NUMBER; i++) {
            if (assaultThiefStatus[i] != null) {
                line += assaultThiefStatus[i] + " " + assaultThiefSituation[i] + "  " + assaultThiefMaxDisp[i] + "    ";
            } else {
                //line += "----" + " " + "-" + "  " + "-" + "    ";
                dontPrint = true;
            }
        }

        line2 += "     ";

        for (int i = 0; i < 2; i++) {
            if (asspartiesRoomID[i] == -1) {
                line2 += "-" + "    ";
            } else {
                line2 += asspartiesRoomID[i] + "    ";
            }

            for (int j = 0; j < MAX_ASSAULT_PARTY_THIEVES; j++) {
                if (assparties[i][j] != -1) {
                    if (asspartiesPos[i][j] < 10) {
                        line2 += (assparties[i][j] + 1) + "   " + asspartiesPos[i][j] + "  " + hasCanvas[assparties[i][j]] + "   ";
                    } else {
                        line2 += (assparties[i][j] + 1) + "  " + asspartiesPos[i][j] + "  " + hasCanvas[assparties[i][j]] + "   ";
                    }
                } else {
                    line2 += "-" + "  " + "--" + "  " + "-" + "   ";
                }
            }
            line2 += "";
        }

        for (int i = 0; i < ROOMS_NUMBER; i++) {
            if (rooms[i].getNPaintings() < 10) {
                line2 += " " + rooms[i].getNPaintings() + " " + rooms[i].getDistOutside() + "   ";
            } else {
                line2 += rooms[i].getNPaintings() + " " + rooms[i].getDistOutside() + "   ";
            }
        }
        if (!dontPrint) {
            if (!(lastLine.equals(line) && lastLine2.equals(line2))) {
                log.writelnString(line);
                log.writelnString(line2);
                lastLine = line;
                lastLine2 = line2;
            }
        }

        if (!log.close()) {
            GenericIO.writelnString("A operação de fecho do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
    }

    /**
     * Log the final status of the Heist.
     *
     */
    @Override
    public synchronized void reportFinalStatus() {
        if (!log.openForAppending(".", fileName)) {
            GenericIO.writelnString("A operação de criação do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }

        log.writelnString("My friends, tonight's effor produced " + nPaintings + " priceless paintings!");
        log.writelnString("\nLegend:");
        log.writelnString("MstT Stat – state of the master thief");
        log.writelnString("Thief # Stat - state of the ordinary thief # (# - 1 .. 6)");
        log.writelnString("Thief # S – situation of the ordinary thief # (# - 1 .. 6) either 'W' (waiting to join a party) or 'P' (in party)");
        log.writelnString("Thief # MD – maximum displacement of the ordinary thief # (# - 1 .. 6) a random number between 2 and 6");
        log.writelnString("Assault party # RId – assault party # (# - 1,2) elem # (# - 1 .. 3) room identification (1 .. 5)");
        log.writelnString("Assault party # Elem # Id – assault party # (# - 1,2) elem # (# - 1 .. 3) member identification (1 .. 6)");
        log.writelnString("Assault party # Elem # Pos – assault party # (# - 1,2) elem # (# - 1 .. 3) present position (0 .. DT RId)");
        log.writelnString("Assault party # Elem # Cv – assault party # (# - 1,2) elem # (# - 1 .. 3) carrying a canvas (0,1)");
        log.writelnString("Museum Room # NP - room identification (1 .. 5) number of paintings presently hanging on the walls");
        log.writelnString("Museum Room # DT - room identification (1 .. 5) distance from outside gathering site, a random number between 15 and 30");

        if (!log.close()) {
            GenericIO.writelnString("A operação de fecho do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
    }
}
