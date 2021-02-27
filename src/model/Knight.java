package model;

public class Knight extends Figure {
    public Knight(Position position, model.Color color) {
        super(position, color);
    }

    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Horse at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Horse at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    @Override
    public String getIcon() {
        if (this.getColor() == model.Color.Black) {
            return "♞";
        }
        return "♘";
    }

    @Override
    protected boolean isValid(Position position) {
        if ((this.position.getX() == (position.getX() - 2) && this.position.getY() == (position.getY() - 1))
                || (this.position.getX() == (position.getX() - 2) && this.position.getY() == (position.getY() + 1))
                || (this.position.getX() == (position.getX() + 2) && this.position.getY() == (position.getY() - 1))
                || (this.position.getX() == (position.getX() + 2) && this.position.getY() == (position.getY() + 1))
                || (this.position.getY() == (position.getY() - 2) && this.position.getX() == (position.getX() - 1))
                || (this.position.getY() == (position.getY() - 2) && this.position.getX() == (position.getX() + 1))
                || (this.position.getY() == (position.getY() + 2) && this.position.getX() == (position.getX() - 1))
                || (this.position.getY() == (position.getY() + 2) && this.position.getX() == (position.getX() + 1))) {
            return true;
        }
        return false;
    }
}