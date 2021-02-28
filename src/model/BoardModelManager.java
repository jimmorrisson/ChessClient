package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.Client;
import controller.Observer;
import view.BoardViewManager;

public class BoardModelManager implements Observer {
    private ArrayList<Figure> board = new ArrayList<>();
    private Figure currentChosenFigure = null;
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
            model.Color color = model.Utils.toColor(figure.getString("color"));
            board.add(Utils.toFigure(type, x, y, color));
        }
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Timer task");
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

    private Figure findFigure(Position position) {
        for (Figure figure : board) {
            if (figure.getPosition().equals(position)) {
                return figure;
            }
        }
        return null;
    }

    @Override
    public void update(Position position) {
    if (currentChosenFigure != null) {
            try {
                String response = client.sendCommand(new Command(currentChosenFigure.getPosition(), position));
                System.out.println(response);
                if (response.equals("Yes")) {
                     currentChosenFigure.setPosition(position);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentChosenFigure = null;
        }

        currentChosenFigure = findFigure(position);
    }
}