import java.util.List;
import java.util.Random;

public class Node {
    private int x; 
    private int y;
    private int id;
    private int numPackets; 
    private int minPackets; 
    private int maxPackets; 
    private boolean isRendezvous = false; 
    private static Random rand = new Random();  // for generating random positions
    
    // Constructor that creates a node with random position
    public Node(int id, int maxWidth, int maxLength, int minPackets, int maxPackets) {
        this.id = id;
        this.x = rand.nextInt(maxWidth + 1);  // random number from 0 to maxWidth-1
        this.y = rand.nextInt(maxLength + 1); // random number from 0 to maxLength-1
        this.numPackets = rand.nextInt(maxPackets - minPackets +1) + minPackets; 
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

    public int getPackets(){ 
        return numPackets;
    }

    public void designateNode(){
        this.isRendezvous = true; 
    }

    public boolean checkIfRendezvous(){
        return isRendezvous;
    }

public static void designate(List<Node> component){
    if(component.size() == 1){
        component.get(0).designateNode(); 
    }
    else{ 
        int designatedIndex = rand.nextInt(component.size()); 
        component.get(designatedIndex).designateNode();
    }
}

    @Override
    public String toString() {
        if(isRendezvous){
            return String.format("Node #%d (%d,%d) %d packets Designated Rendezvous", this.getId(), this.getX(), this.getY(), this.getPackets());
        }
        else{ 
            return String.format("Node #%d (%d,%d) %d packets", this.getId(), this.getX(), this.getY(), this.getPackets());
        }
    }
}