import org.json.JSONObject;

import chess.com.Position;

public class Rook extends Figure {

    
    /** 
     * @param position
     * @param color
     */
    public Rook(Position position, Color color) {
        super(position, color);
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean move(Position position) {
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♜";
        }
        return "♖";
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean isValid(Position position) {
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("type", "Rook");
        object.put("color", getColor().toString());
        object.put("x", getPosition().getX());
        object.put("y", getPosition().getY());
        return object.toString();
    }
}
