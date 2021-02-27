package model;

public abstract class Figure {
    protected Position position;
    private model.Color color;

    public Figure(Position position, model.Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public model.Color getColor() {
        return color;
    }

    public abstract boolean move(Position position);

    public abstract String getIcon();

    public void setPosition(Position position) {
        this.position = position;
    }

    protected abstract boolean isValid(Position position);
}