package project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class paint extends JPanel {

    //map variable
    private int[][] map = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, -1, 0, -1, 0, -1, 0, -1, -1, 0},
        {0, -1, 0, -1, -1, -1, 0, -1, 0, 0},
        {0, -1, -1, 0, -1, 0, 0, -1, -1, 0},
        {0, -1, 0, -1, -1, -1, 0, 0, -1, 0},
        {0, -1, 0, -1, 0, -1, -1, -1, 0, 0},
        {0, -1, 0, 0, -1, -1, -1, -1, 0, 0},
        {0, -1, 0, -1, 0, -1, 0, -1, 0, 0},
        {0, -1, 0, -1, -1, -1, -1, -1, -1, 0},
        {0, -1, 0, 0, -1, -1, -1, -1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private int cellSize = 40;
    private double translateX;
    private double translateY;
    private double scale;

    private node start = new node(0, 0);//null;
    private node finish = new node(5, 0);//null;
    private ArrayList<node[]> shortestPathSolution = null;
    private String stateSolusi = "DFS";//DFS

    paint() {
        translateX = 0;
        translateY = 0;
        scale = 1;
        setOpaque(false);
        setDoubleBuffered(true);
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int[][] getLabyrinth() {
        return map;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public node getStart() {
        return start;
    }

    public void setStart(node start) {
        this.start = start;
    }

    public node getFinish() {
        return finish;

    }

    public void setFinish(node finish) {
        this.finish = finish;
    }

    public void resetGrid(int rows, int cols){
        map = new int[rows][cols];
        repaint();
    }
    
    public void randomMap(int rows, int cols) {
        int min = -1;
        int high = 0;
        Random rnd = new Random();
        map = new int[rows][cols];

        for (int i = 0; i < rows - 1; i = i + 2) {
            for (int j = 0; j < cols - 1; j++) {
                int n = rnd.nextInt((high - min) + 1) + min;
                map[i][j] = n;
            }
        }
        repaint();
    }


    public void setStart(int x, int y) {
        this.start = new node(x, y);
    }

    public void setFinish(int x, int y) {
        this.finish = new node(x, y);
    }

    //BFS Solution    
    public void BreadthFirstSearch() {
        if (map != null && start != null && finish != null) {
            shortestPathSolution = BFS_Algorithm.breadthFirstSearch(map, start, finish);
            stateSolusi = "BFS";
            repaint();
        }
    }

    public void solve() {
        BreadthFirstSearch();
    }
    

    @Override
    public void paint(Graphics g) {
           
        AffineTransform tx = new AffineTransform();
        tx.translate(translateX, translateY);
        tx.scale(scale, scale);
        
        Graphics2D p = (Graphics2D) g;

        p.translate(50, 50);
        p.setColor(Color.WHITE);
        p.fillRect(0, 0, getWidth(), getHeight());
        
        p.setTransform(tx);
        p.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        p.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        //drawing the map
        int rows = map.length;
        int cols = map[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = map[i][j];
                switch(value){
                        case -1:
                            p.setColor(Color.GRAY);
                            break;
                        case 0:
                            p.setColor(Color.WHITE);
                            break;
                    }
                p.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                p.setColor(Color.decode("#95a5a6"));
                p.setStroke(new BasicStroke(1));
                p.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        //shortest path
        if (shortestPathSolution != null) {
            for (node[] edge : shortestPathSolution) {
                node origin = edge[0];
                node destination = edge[1];

                p.setColor(Color.decode("#3498db"));
                p.fillRect(destination.y * cellSize, destination.x * cellSize, cellSize, cellSize);

                int cx1 = origin.x * cellSize + (int) (0.5 * cellSize);
                int cy1 = origin.y * cellSize + (int) (0.5 * cellSize);
                int cx2 = destination.x * cellSize + (int) (0.5 * cellSize);
                int cy2 = destination.y * cellSize + (int) (0.5 * cellSize);
                p.setColor(Color.YELLOW);
                p.setStroke(new BasicStroke(2));
                p.drawLine(cy1, cx1, cy2, cx2);
            }
        }

        //drawing start and finish
        if (finish != null && finish.x >= 0 && finish.x < map.length && finish.y >= 0 && finish.y < map[0].length) {
            p.setColor(Color.decode("#1abc9c"));
            p.fillRect(finish.y * cellSize, finish.x * cellSize, cellSize, cellSize);
            p.setColor(Color.decode("#95a5a6"));
            p.setStroke(new BasicStroke(1));
            p.drawRect(finish.y * cellSize, finish.x * cellSize, cellSize, cellSize);
        }
        if (start != null && start.x >= 0 && start.x < map.length && start.y >= 0 && start.y < map[0].length) {
            p.setColor(Color.decode("#e74c3c"));
            p.fillRect(start.y * cellSize, start.x * cellSize, cellSize, cellSize);
            p.setColor(Color.decode("#95a5a6"));
            p.setStroke(new BasicStroke(1));
            p.drawRect(start.y * cellSize, start.x * cellSize, cellSize, cellSize);
        }

        //---------------------------------------------------------------------------------------------------
        p.dispose();
    }//END of PAINT
}
