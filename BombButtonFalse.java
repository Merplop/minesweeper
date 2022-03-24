import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

public class BombButtonFalse extends BombButton implements MouseListener { 
    public BombButtonFalse() {
    }
    
    public boolean containsBomb() { 
        return false;
    }
    
    public void setBombIcon() {}
}