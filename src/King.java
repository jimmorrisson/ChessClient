import chess.com.Position;

public class King extends Figure {

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. King at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! King at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♚";
        }
        return "♔";
    }

    @Override
    public boolean isValid(Position position) {
        return false;
    }

}