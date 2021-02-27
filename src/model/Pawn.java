package model;

public class Pawn extends Figure {
    private boolean hasMoved = false;

    public Pawn(Position position, model.Color color) {
        super(position, color);
    }

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

    @Override
    public String getIcon() {
        if (this.getColor() == model.Color.Black) {
            return "♟︎";        
        } 
        return "♙";
    }

    @Override
    protected boolean isValid(Position position) {
        if (hasMoved == false) {
            return (this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY())
                    || (this.position.getX() == position.getX() && (this.position.getY() + 2) == position.getY());
        }
        return this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY();
    }
}