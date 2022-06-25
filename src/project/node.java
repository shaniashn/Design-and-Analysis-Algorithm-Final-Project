package project;

public class node {
    int x, y;
    
    public node(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
    
    public boolean compare(node anotherNode){
        if(anotherNode.x == x && anotherNode.y == y){
            return true;
        }
        return false;
    }
}
