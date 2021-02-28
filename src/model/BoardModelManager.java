package model;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import app.Client;
import controller.Observer;
import view.BoardViewManager;

public class BoardModelManager implements Observer {
    private ArrayList<Figure> board = new ArrayList<>();
    private Figure currentChosenFigure = null;
    private Client client;

    public BoardModelManager(Client client) {
        this.client = client;
    }

    public void initializeBoard(JSONArray figures) {
        for (int n = 0; n < figures.length(); n++) {
            JSONObject figure = new JSONObject(figures.getString(n));

            // JSONObject figure = figures.getJSONObject(n);
            String type = figure.getString("type");
            int x = figure.getInt("x");
            int y = figure.getInt("y");
            String color = figure.getString("color");
            if (type.equals("Pawn")) {
                if (color.equals("White")) {
                    board.add(new Pawn(new Position(x, y), model.Color.White));
                } else {
                    board.add(new Pawn(new Position(x, y), model.Color.Black));
                }

            } else if (type.equals("Knight")) {
                if (color.equals("White")) {
                    board.add(new Knight(new Position(x, y), model.Color.White));
                } else {
                    board.add(new Knight(new Position(x, y), model.Color.Black));
                }
            }
        }
        // board.add(new Knight(new Position(1, 0), model.Color.White));
        // board.add(new Pawn(new Position(1, 1), model.Color.White));
        // board.add(new Pawn(new Position(2, 1), model.Color.White));
        // board.add(new Pawn(new Position(3, 1), model.Color.White));
        // board.add(new Pawn(new Position(4, 1), model.Color.White));
        // board.add(new Pawn(new Position(5, 1), model.Color.White));
        // board.add(new Knight(new Position(6, 0), model.Color.White));
        // board.add(new Pawn(new Position(6, 1), model.Color.White));
        // board.add(new Pawn(new Position(7, 1), model.Color.White));

        // board.add(new Pawn(new Position(0, 6), model.Color.Black));
        // board.add(new Knight(new Position(1, 7), model.Color.Black));
        // board.add(new Pawn(new Position(1, 6), model.Color.Black));
        // board.add(new Pawn(new Position(2, 6), model.Color.Black));
        // board.add(new Pawn(new Position(3, 6), model.Color.Black));
        // board.add(new Pawn(new Position(4, 6), model.Color.Black));
        // board.add(new Pawn(new Position(5, 6), model.Color.Black));
        // board.add(new Knight(new Position(6, 7), model.Color.Black));
        // board.add(new Pawn(new Position(6, 6), model.Color.Black));
        // board.add(new Pawn(new Position(7, 6), model.Color.Black));
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
                    //currentChosenFigure.move(position);
                     currentChosenFigure.setPosition(position);
                     JSONObject board = new JSONObject(client.getBoardContext().toString());
                     BoardViewManager.refreshBoard(board);
                    //BoardViewManager.refresh();
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