import java.util.List;

public interface Graph {
    void addEdge(int sourceNode, int connectedNode);
    

    //Breadth first search algorithm 
    List<List<Node>> bfs();

    //Depth first search algorithm 
    List<List<Node>> dfs();
}