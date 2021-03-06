
import chess.com.*;

public class Utils {
    
    /** 
     */
    private Utils() {
    }

    
    /** Converts string to the color enum
     * @param color
     * @return Color
     */
    public static Color toColor(String color) {
        if (color.toLowerCase().equals("white")) {
            return Color.White;
        } else if (color.toLowerCase().equals("black")) {
            return Color.Black;
        }
        return null;
    }

    
    /** Creates figure from the parameter passed.
     * @param type
     * @param position
     * @param color
     * @return Figure
     */
    public static Figure toFigure(String type, Position position, Color color) {
        if (type.equals("Pawn")) {
            return new Pawn(position, color);
        } else if (type.equals("Knight")) {
            return new Knight(position, color);
        }
        else if (type.equals("Rook")) {
            return new Rook(position, color);
        }
        else if (type.equals("Bishop")) {
            return new Bishop(position, color);
        }
        else if (type.equals("King")) {
            return new King(position, color);
        }
        else if (type.equals("Queen")) {
            return new Queen(position, color);
        }
        return null;
    }

    
    /** Creates figure from the parameter passed.
     * @param type
     * @param position
     * @param color
     * @return Figure
     */
    public static Figure toFigure(String type, Position position, String color) {
        return Utils.toFigure(type, position, Utils.toColor(color));
    }

    
    /** Creates figure from the parameter passed.
     * @param type
     * @param x
     * @param y
     * @param color
     * @return Figure
     */
    public static Figure toFigure(String type, int x, int y, Color color) {
        return Utils.toFigure(type, new Position(x, y), color);
    }

    
    /** Creates figure from the parameter passed.
     * @param type
     * @param x
     * @param y
     * @param color
     * @return Figure
     */
    public static Figure toFigure(String type, int x, int y, String color) {
        return Utils.toFigure(type, new Position(x, y), Utils.toColor(color));
    }
}
