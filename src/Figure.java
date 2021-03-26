import chess.com.Position;

public abstract class Figure {
    protected Position position;
    private Color color;

    
    /** Base figure interface
     * @param position starting position
     * @param color color of the figure
     */
    public Figure(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    /** Gets current position of the figure
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /** Gets the color of the figure
     * @return Color
     */
    public Color getColor() {
        return color;
    }
    
    /** Handles move figure to the passed position
     * @param position Desired position to move to.
     * @return boolean True if move is done. False otherwise.
     */
    public abstract boolean move(Position position);
    
    /**  Gets unicode of the figure
     * @return String
     */
    public abstract String getIcon();

    /** Sets position of the figure to the position passed
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    protected abstract boolean isValid(Position position);
}
