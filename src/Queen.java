import chess.com.Position;

public class Queen extends Figure {

    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Queen at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Queen at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♛";
        }
        return "♕";
    }

    @Override
    public boolean isValid(Position position) {
        return false;
    }

}
