import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;

public class SensorNetworkRunner {
    
    public static void main(String[] args){ 
        Scanner scan = new Scanner(System.in);
        int numNodes, width, length, transmissionRange,  graphChoice, algoChoice, minPackets, maxPackets; 

        //Getting user input 
        System.out.println("Please enter width of sensor network:");
        width = scan.nextInt();
        System.out.println("Please enter length of sensor network:");
        length = scan.nextInt();
        System.out.println("Please enter number of nodes in sensor network:");
        numNodes = scan.nextInt();
        System.out.println("Please enter transmission range:");
        transmissionRange = scan.nextInt();
        System.out.println("Type 1 for adjacency matrix or 2 for adjacency list:");
        graphChoice = scan.nextInt();
        System.out.println("Type 1 for breadth first search or 2 for depth first search. You can also Type 3 for both:");
        algoChoice = scan.nextInt();
        System.out.println("Please enter maximum number of data packets per node:");
        maxPackets = scan.nextInt();
        System.out.println("Please enter minimum number of data packets per node:");
        minPackets= scan.nextInt();

        scan.close(); 

        //Initializing graph based on user input
        Graph graph = null; 
        if(graphChoice == 1){ 
            graph = new MatrixGraph(numNodes);
        }
        else if(graphChoice == 2){ 
            graph = new ListGraph(numNodes);
        }
        else{ 
            System.out.println("Pick a valid number next time!!");
            System.exit(0);
        }

        //Initializing nodes 
        List<Node> nodeList = new ArrayList<Node>(numNodes);
        for(int i = 1; i <= numNodes; i++){ 
            Node newNode = new Node(i, width, length, minPackets, maxPackets);
            nodeList.add(newNode);
            NodeRegistry.registerNode(newNode);
        }

        //If a pair of nodes are in distance of each other, add them to the graph
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                if (nodeList.get(i).getDistance(nodeList.get(j)) <= transmissionRange) {
                    graph.addEdge(nodeList.get(i).getId(), j+1);
                }
            }
        }

        List<List<Node>> components = null;
        List<List<Node>> components2 = null; 
        if(algoChoice == 1){
           components = graph.bfs(); 
        }
        else if(algoChoice == 2){
            components = graph.dfs(); 
        }
        else if(algoChoice == 3){
            components = graph.bfs();
            components2 = graph.dfs(); 
        }
        else{ 
            System.out.println("Pick a valid number next time!!!");
            System.exit(0);
        }

       for(List<Node> component : components){
        DesignateRendezvous.designate(component);
       }

       if(algoChoice == 3){
        for(List<Node> component : components2){
            DesignateRendezvous.designate(component);
           }
       }

        if(components.size() > 1){ 
            System.out.println("Graph is not connected");
        }
        else{ 
            System.out.println("Graph is connected");
        }

        if(algoChoice == 1 || algoChoice == 3) {System.out.println("\nExecuting Breadth First Search:");}
        else if(algoChoice == 2){System.out.println("\nExecuting Depth First Search:");}

        for(List<Node> component : components){
            System.out.println("Connected Component:" + component);
        }

        if(algoChoice == 3){ 
            System.out.println("\nExecuting Depth First Search:");
            for(List<Node> component : components2){
                System.out.println("Connected component: " + component);
            }
        }
    }
}
