import chess.com.Position;

public class Pawn extends Figure {
    private boolean hasMoved = false;

    
    /** 
     * @param position
     * @param color
     */
    public Pawn(Position position, Color color) {
        super(position, color);
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Pawn at position " + this.position.getX() + "," + this.position.getY());
            hasMoved = true;
            return true;
        }
        System.out.print("Move incorrect! Pawn at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♟";
        }
        return "♙";
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    protected boolean isValid(Position position) {
        if (hasMoved == false) {
            return (this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY())
                    || (this.position.getX() == position.getX() && (this.position.getY() + 2) == position.getY());
        }
        return this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY();
    }
}
