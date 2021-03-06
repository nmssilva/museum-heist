package serverSide.interfaces;

import auxiliary.messages.*;
import serverSide.Museum;
import static auxiliary.messages.Message.*;

public class Museum_Interface extends Interface {

    /**
     * Concentration site (represents the provided service)
     *
     * @serialField cs
     */
    private Museum museum;

    /**
     * Concentration site interface instantiation
     *
     * @param museum
     */
    public Museum_Interface(Museum museum) {
        this.museum = museum;
    }

    /**
     * Processamento das mensagens através da execução da tarefa correspondente.
     * Geração de uma mensagem de resposta.
     *
     * @param inMessage mensagem com o pedido
     *
     * @return mensagem de resposta
     *
     * @throws MessageException se a mensagem com o pedido for considerada
     * inválida
     */
    @Override
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;                           // mensagem de resposta

        /* seu processamento */
        switch (inMessage.getType()) {
            case GET_DIST_OUTSIDE:
                //System.out.println("MUSEUM - GET DIST OUTSIDE Room:" + inMessage.getValue());
                int distOutside = museum.getRoom(inMessage.getValue()).getDistOutside();

                outMessage = new Message(Message.ACK, distOutside);
                break;
            case ROLL_A_CANVAS:
                //System.out.println("MUSEUM - ROLL_A_CANVAS");
                int hasCanvas = museum.rollACanvas(inMessage.getValue());

                outMessage = new Message(Message.ACK, hasCanvas);
                break;
        }

        return outMessage;
    }
}
