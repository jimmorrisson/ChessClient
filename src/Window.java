
import chess.com.Position;

import java.awt.*;
import java.awt.Color;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Window extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Button> buttons = new ArrayList<>();

    public Window(ArrayList<Figure> context, Observer observer, String playerColor) {
        super(playerColor);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(0, 8));

        for (int col = 7; col >= 0; col--) {
            for (int row = 0; row < 8; row++) {
                Position position = new Position(row, col);
                Color color = Color.WHITE;
                Figure figure = null;
                for (Figure f : context) {
                    if (f.getPosition().equals(position)) {
                        figure = f;
                        break;
                    }
                }
                if (((row + col) % 2) == 0) {
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                }
                if (figure != null) {
                    Button button = new Button(figure.getIcon(), position, color);
                    button.addObserver(observer);
                    buttons.add(button);
                    this.add(button);
                } else {
                    Button button = new Button(null, position, color);
                    button.addObserver(observer);
                    buttons.add(button);
                    this.add(button);
                }
            }
        }
    }

    public void refreshContext(JSONObject board) {
        JSONArray figures = board.getJSONArray("board");
        for (Button button : buttons) {
            button.setText("");
            for (int n = 0; n < figures.length(); n++) {
                JSONObject figure = new JSONObject(figures.getString(n));
                String type = figure.getString("type");
                int x = figure.getInt("x");
                int y = figure.getInt("y");
                String color = figure.getString("color");
                Position figurePosition = new Position(x, y);
                if (button.getPosition().equals(figurePosition)) {
                    button.setText(Utils.toFigure(type, figurePosition, color).getIcon());
                }
            }
        }
        System.out.println(board.toString());
    }
}