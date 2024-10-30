import java.util.List;

public interface Graph {
    void addEdge(int sourceNode, int connectedNode);
    

    //Breadth first search algorithm 
    List<List<Integer>> bfs();

    //Depth first search algorithm 
    List<List<Integer>> dfs();
}