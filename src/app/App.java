package app;

import org.json.JSONArray;

import model.BoardModelManager;
import view.BoardViewManager;

public class App {
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 4441);
        BoardModelManager boardManager = new BoardModelManager(client);
        JSONArray figures = client.getBoardContext().getJSONArray("board");
        boardManager.initializeBoard(figures);
        System.out.println(client.getPlayerName());
        BoardViewManager.initializeBoard(boardManager.getContext(), boardManager);
        // client.stopConnection();
   }
}