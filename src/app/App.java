package app;

import model.BoardModelManager;
import view.BoardViewManager;

public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 4441);
        BoardModelManager boardManager = new BoardModelManager(client);
        boardManager.initializeBoard();
        BoardViewManager.initializeBoard(boardManager.getContext(), boardManager);
        System.out.println(client.sendMessage("Player white"));
        // client.stopConnection();
   }
}