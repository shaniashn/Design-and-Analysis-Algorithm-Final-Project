package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class panelWindow extends javax.swing.JFrame{
    //variable
    private JPanel jContentPane = null;
    private JPanel jPanelNorth = null;
    private paint dPanel = null;
    private JButton jButtonStart = null;
    private JButton jButtonGenerateMap = null;
    private JButton jButtonReset = null;
    private JButton jButtonSolusiBFS = null;
    
    int[][] arr;

    public panelWindow() {
        init();
    }

    public void init() {
        setTitle("DAA project Smart Courier (BFS)");
        setSize(500, 550);
        setContentPane(getJContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanelNorth(), BorderLayout.NORTH);
            jContentPane.add(getCanvas(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    private JPanel getJPanelNorth() {
        if (jPanelNorth == null) {
            jPanelNorth = new JPanel();
            jPanelNorth.setLayout(new FlowLayout());

            jPanelNorth.add(getJButtonStart());
            jPanelNorth.add(getJButtonGenerateMap());
            jPanelNorth.add(getJButtonReset());
        }
        return jPanelNorth;
    }
    
    private JButton getJButtonStart() {
        if (jButtonStart == null) {
            jButtonStart = new JButton("Start");
            jButtonStart.setPreferredSize(new Dimension(100, 30));
            jButtonStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    dPanel.BreadthFirstSearch();
                }
            });
        }
        return jButtonStart;
    }
    
    private paint getCanvas() {
        if (dPanel == null) {
            dPanel = new paint();
            actionhandler handler = new actionhandler(this, dPanel);
            
            dPanel.addMouseListener(handler);
            dPanel.addMouseMotionListener(handler);
            dPanel.addKeyListener(handler);
        }
        return dPanel;
    }
    
    private JButton getJButtonReset(){
        if (jButtonReset == null){
            jButtonReset = new JButton("Clear Blocks");
            jButtonReset.setPreferredSize(new Dimension(120, 30));
            jButtonReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rows = 10;
                    int cols = 10;
                    dPanel.resetGrid(rows, cols);
 
                }
            });
        }
        
        return jButtonReset;
    }
    
    private JButton getJButtonGenerateMap() {
        if (jButtonGenerateMap == null) {
            jButtonGenerateMap = new JButton("Generate Map");
            jButtonGenerateMap.setPreferredSize(new Dimension(120, 30));
            jButtonGenerateMap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rows = 10;
                    int cols = 10;
                    dPanel.randomMap(rows, cols);

                }
            });
        }
        return jButtonGenerateMap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                panelWindow p = new panelWindow();
            }
        });
    }
}
