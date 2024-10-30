import java.util.Random;

public class Node {
    private int x; 
    private int y;
    private int id;
    private static Random rand = new Random();  // for generating random positions
    
    // Constructor that creates a node with random position
    public Node(int id, int maxWidth, int maxLength) {
        this.id = id;
        this.x = rand.nextInt(maxWidth + 1);  // random number from 0 to maxWidth-1
        this.y = rand.nextInt(maxLength + 1); // random number from 0 to maxLength-1
    }
    
    public double getDistance(Node other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx*dx + dy*dy);  // sqrt((x2-x1)^2 + (y2-y1)^2)
    }

    public int getX(){
        return x; 
    }

    public int getY(){
        return y; 
    }
    public int getId(){ 
        return id; 
    }
}