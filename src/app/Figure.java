

public abstract class Figure {
    protected Position position;
    private Color color;

    public Figure(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean move(Position position);

    public abstract String getIcon();

    public void setPosition(Position position) {
        this.position = position;
    }

    protected abstract boolean isValid(Position position);
}