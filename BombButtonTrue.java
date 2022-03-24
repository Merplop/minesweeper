import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

public class BombButtonTrue extends BombButton implements MouseListener { 
    public BombButtonTrue() {     
    }
    
    public boolean containsBomb() { 
        return true;
    }
    
    public void setBombIcon() {
        this.setIcon(bomb);
    }
}
