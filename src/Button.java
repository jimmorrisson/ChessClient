
import chess.com.Position;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button extends JButton implements ActionListener, Observable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Observer> objectsToNotify = new ArrayList<Observer>();
    private Position position;

    
    /** 
     * @param icon
     * @param position
     * @param color
     */
    public Button(String icon, Position position, Color color) {
        super(icon);
        this.position = position;
        setBackground(color);
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(this);
    }

    
    /** 
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    
    /** 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        notifyObserver(position);
    }

    
    /** 
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        objectsToNotify.add(o);
    }

    
    /** 
     * @param o
     */
    @Override
    public void removeObserver(Observer o) {
        objectsToNotify.remove(o);
    }

    
    /** 
     * @param position
     */
    @Override
    public void notifyObserver(Position position) {
        for (Observer o : objectsToNotify) {
            o.update(position);
        }
    }
}
