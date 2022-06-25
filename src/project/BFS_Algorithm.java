package project;

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Stack;
import java.util.*;

public class BFS_Algorithm {

    private static int[][] clone(int[][] arrSatu) {
        int[][] arrDua = null;
        if (arrSatu != null) {
            arrDua = new int[arrSatu.length][];
            for (int i = 0; i < arrSatu.length; i++) {
                arrDua[i] = new int[arrSatu[i].length];
                for (int j = 0; j < arrSatu[i].length; j++) {
                    arrDua[i][j] = arrSatu[i][j];
                }
            }
        }
        return arrDua;
    }

    public static ArrayList<node[]> breadthFirstSearch(int[][] map, node start, node finish) {

        //step variable
        int step = 1;

        ArrayList<node[]> list = new ArrayList<>();

        int[][] pathfinder = clone(map);
        int noRows = pathfinder.length;
        int noCols = pathfinder[0].length;

        LinkedList<node> nodeList = new LinkedList<>();
        nodeList.add(start);

        pathfinder[finish.x][finish.y] = 0;
        pathfinder[start.x][start.y] = step;

        boolean selesai = false;

        searching:
        while (!selesai && !nodeList.isEmpty()) {
            node center = nodeList.pollFirst();
            int x = center.x;
            int y = center.y;

            step = pathfinder[x][y];
            step++;

            //north path
            int i = x - 1;
            int j = y;

            if (i >= 0 && i < noRows && j >= 0 && j < noCols && pathfinder[i][j] == 0) {
                pathfinder[i][j] = step;
                node newNode = new node(i, j);
                nodeList.add(newNode);
                node[] edge = {center, newNode};
                list.add(edge);
                if (finish.compare(newNode)) {
                    selesai = true;
                    break searching;
                }
            }

            //east path
            i = x;
            j = y + 1;

            if (i >= 0 && i < noRows && j >= 0 && j < noCols && pathfinder[i][j] == 0) {
                pathfinder[i][j] = step;
                node newNode = new node(i, j);
                nodeList.add(newNode);
                node[] edge = {center, newNode};
                list.add(edge);
                if (finish.compare(newNode)) {
                    selesai = true;
                    break searching;
                }
            }

            //south path
            i = x + 1;
            j = y;

            if (i >= 0 && i < noRows && j >= 0 && j < noCols && pathfinder[i][j] == 0) {
                pathfinder[i][j] = step;
                node newNode = new node(i, j);
                nodeList.add(newNode);
                node[] edge = {center, newNode};
                list.add(edge);
                if (finish.compare(newNode)) {
                    selesai = true;
                    break searching;
                }
            }

            //west path
            i = x;
            j = y - 1;

            if (i >= 0 && i < noRows && j >= 0 && j < noCols && pathfinder[i][j] == 0) {
                pathfinder[i][j] = step;
                node newNode = new node(i, j);
                nodeList.add(newNode);
                node[] edge = {center, newNode};
                list.add(edge);
                if (finish.compare(newNode)) {
                    selesai = true;
                    break searching;
                }
            }
        }

        return list;
    }
}
