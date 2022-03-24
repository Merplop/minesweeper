import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

public abstract class BombButton extends JButton implements MouseListener { 
    ImageIcon grey, bomb, flag;
    public int deleteState = 0;
    
    public BombButton() {
        bomb = new ImageIcon(this.getClass().getResource("bomb.png"));
        grey = new ImageIcon(this.getClass().getResource("grey.png"));
        flag = new ImageIcon(this.getClass().getResource("flag.png"));
        this.addMouseListener(this);
        this.setIcon(grey);
    }
    
    public abstract boolean containsBomb();
    
    public int getDeleteState() { 
        return deleteState;
    }
    
    public void setDeleteState(int d) {
        deleteState = d;
    }
    
    public abstract void setBombIcon();
    
    boolean pressed;
    
    public void mousePressed(MouseEvent e) {
        this.getModel().setArmed(true);
        this.getModel().setPressed(true);
        pressed = true;        
    }
    
    public void mouseReleased(MouseEvent e) {
        this.getModel().setArmed(false);
        this.getModel().setPressed(true);
        
        if (pressed) {
            if (SwingUtilities.isRightMouseButton(e)) {
                this.setIcon(flag);
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                deleteState = 1;
            }
        }
        pressed = false;
    }
    
    public void mouseExited(MouseEvent e) {
        pressed = false;
    }
    
    public void mouseEntered(MouseEvent e) {
        pressed = true;
    }
    
    public void mouseClicked(MouseEvent e) {}
}