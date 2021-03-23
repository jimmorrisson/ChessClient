
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import chess.com.CommandSetPosition;
import chess.com.Position;

public class BoardModelManager implements Observer {
    private ArrayList<Figure> board = new ArrayList<>();
    private Position currentChosenPosition = null;
    private Client client;
    private Timer refreshTimer;

    public BoardModelManager(Client client) {
        this.client = client;
        refreshTimer = new Timer();
    }

    public void initializeBoard(JSONArray figures) {
        for (int n = 0; n < figures.length(); n++) {
            JSONObject figure = new JSONObject(figures.getString(n));
            String type = figure.getString("type");
            int x = figure.getInt("x");
            int y = figure.getInt("y");
            Color color = Utils.toColor(figure.getString("color"));
            board.add(Utils.toFigure(type, x, y, color));
        }
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject board = new JSONObject(client.getBoardContext().toString());
                    BoardViewManager.refreshBoard(board);
                } catch (JSONException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 2000, 1000);
    }

    public ArrayList<Figure> getContext() {
        return board;
    }
    
    @Override
    public void update(Position position) {
    if (currentChosenPosition != null) {
            try {
                String response = client.sendCommand(new CommandSetPosition(currentChosenPosition, position));
                System.out.println(response);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentChosenPosition = null;
        }

        currentChosenPosition = position;
    }
}
