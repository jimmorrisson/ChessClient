import chess.com.Position;

public class Bishop extends Figure {

    
    /** Bishop constructor
     * @param position
     * @param color
     */
    public Bishop(Position position, Color color) {
        super(position, color);
    }

    
    /** Handles move bishop to the passed position
     * @param position
     * @return boolean True if moved, false otherwise
     */
    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Bishop at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Bishop at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♝";
        }
        return "♗";
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean isValid(Position position) {
        return false;
    }
}
