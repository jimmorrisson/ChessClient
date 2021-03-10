

import java.awt.Dimension;
import javax.swing.JFrame;

import org.json.JSONObject;

import java.util.ArrayList;

public class BoardViewManager {
    private BoardViewManager() {
    }

    private static Window window;

    public static void initializeBoard(ArrayList<Figure> context, Observer observer, String playerColor) {
        window = new Window(context, observer, playerColor);
        window.setPreferredSize(new Dimension(1000, 600));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void refreshBoard(JSONObject board){
        window.refreshContext(board);
    }
}
