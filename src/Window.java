
import chess.com.Position;

import java.awt.*;
import java.awt.Color;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Window extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Button> buttons = new ArrayList<>();
    private Label lblWhiteTime;
    private Label lblBlackTime;

    protected void addbutton(Button button, GridBagLayout gridbag, GridBagConstraints c) {
        gridbag.setConstraints(button, c);
        buttons.add(button);
        add(button);
    }

    public Window(ArrayList<Figure> context, Observer observer, String playerColor) {
        super(playerColor);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container panel = getContentPane();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        panel.setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 4;
        c.weighty = 1.0;

        lblBlackTime = new Label("Test");
        gridbag.setConstraints(lblBlackTime, c);
        this.add(lblBlackTime);
        c.gridy = 4;
        lblWhiteTime = new Label("Test");
        gridbag.setConstraints(lblWhiteTime, c);
        this.add(lblWhiteTime);
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
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
                    if (row == 0) {
                        c.gridy += 1;
                        c.gridx = 1;
                    } else {
                        c.gridx += 1;
                    }
                    addbutton(button, gridbag, c);
                } else {
                    Button button = new Button(null, position, color);
                    button.addObserver(observer);

                    if (row == 0) {
                        c.gridy += 1;
                        c.gridx = 1;
                    } else {
                        c.gridx += 1;
                    }
                    addbutton(button, gridbag, c);
                }
            }
        }
    }

    public void refreshContext(JSONObject board) {
        int whiteTimeLeft = board.getInt("time_white");
        lblWhiteTime.setText("Time left: " + whiteTimeLeft);
        int blackTimeLeft = board.getInt("time_black");
        lblBlackTime.setText("Time left: " + blackTimeLeft);
        String whiteState = board.getString("player_White");
        if (whiteState.contains("lost")) {
            handleEnd("Player black won!");
        }
        try {
            String blackState = board.getString("player_Black");
            if (blackState.contains("lost")) {
                handleEnd("Player white won!");
            }
        } catch (JSONException e) {

        }
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
    }

    private void addComponentsToThePane(Container pane, String text) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JLabel endTextLabel = new JLabel(text);
        this.add(endTextLabel);
    }

    private void handleEnd(String endText) {
        Container pane = this.getContentPane();
        pane.removeAll();
        pane.repaint();
        addComponentsToThePane(pane, endText);
        this.pack();
        this.setVisible(true);
    }
}
