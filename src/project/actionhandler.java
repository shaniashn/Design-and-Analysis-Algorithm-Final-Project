package project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;

public class actionhandler implements KeyListener, MouseListener, MouseMotionListener{
    private JFrame Frame;
    private paint dPanel;

    private int lastOffsetX;
    private int lastOffsetY;

    //clicking state
    private int stateClick = 0;//0=idle; 1 = set start; 2 = set finish

    public actionhandler (JFrame Frame, paint dPanel) {
        this.Frame = Frame;
        this.dPanel = dPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        double x1 = e.getX() - dPanel.getTranslateX();
        double y1 = e.getY() - dPanel.getTranslateY();

        double scale = dPanel.getScale();
        double cellSizeScale = dPanel.getCellSize() * scale;
        double x = Math.floor(x1 / cellSizeScale);
        double y = Math.floor(y1 / cellSizeScale);

        int i = (int) y;
        int j = (int) x;

        if (stateClick == 0) {
            if ((new node(i, j)).compare(dPanel.getStart())) {
                stateClick = 1;
            } else if ((new node(i, j)).compare(dPanel.getFinish())) {
                stateClick = 2;
            } else {
                if (dPanel.getLabyrinth() != null && i >= 0 && i < dPanel.getLabyrinth().length && j >= 0 && j < dPanel.getLabyrinth()[0].length) {
                    if (dPanel.getLabyrinth()[i][j] == 0) {
                        dPanel.getLabyrinth()[i][j] = -1;
                    } else {
                        dPanel.getLabyrinth()[i][j] = 0;
                    }
                    dPanel.solve();
                }
            }
        } else if (stateClick == 1) {
            dPanel.setStart(i, j);
            stateClick = 0;
            dPanel.solve();
        } else if (stateClick == 2) {
            dPanel.setFinish(i, j);
            stateClick = 0;
            dPanel.solve();
        }
        dPanel.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //to determine the start position
        lastOffsetX = e.getX();
        lastOffsetY = e.getY();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        // defenisikan posisi x dan y yang baru
        // hitung translasi x dan y
        int newX = e.getX() - lastOffsetX;
        int newY = e.getY() - lastOffsetY;

        // increment last offset oleh even drag mouse
        lastOffsetX += newX;
        lastOffsetY += newY;

        // update posisi canvas
        dPanel.setTranslateX(dPanel.getTranslateX() + newX);
        dPanel.setTranslateY(dPanel.getTranslateY() + newY);

        // schedule a repaint.
        dPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
