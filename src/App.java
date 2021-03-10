import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

public class App {
    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 4441);
            BoardModelManager boardManager = new BoardModelManager(client);
            JSONArray figures = client.getBoardContext().getJSONArray("board");
            boardManager.initializeBoard(figures);
            String playerColor = client.getPlayerName();
            BoardViewManager.initializeBoard(boardManager.getContext(), boardManager, playerColor);    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // client.stopConnection();
   }
}