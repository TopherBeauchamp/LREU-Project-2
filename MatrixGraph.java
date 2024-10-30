import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//Adjacency matrix class from https://www.programiz.com/dsa/graph-adjacency-matrix (BFS and DFS algorithms are my own)

public class MatrixGraph implements Graph {
    private Edge adjMatrix[][];
    private int numVertices;
  
    // Initialize the matrix
    MatrixGraph(int numVertices) {
      this.numVertices = numVertices;
      adjMatrix = new Edge[numVertices][numVertices];
    }
  
    // Add edges - subtract 1 from indexes to convert from 1 - numVertices based nodeList to 0 - numVertices -1 based adjMatrix 
    public void addEdge(Node sourceNode, Node connectedNode) {
      adjMatrix[sourceNode.getId()-1][connectedNode.getId()-1] = new Edge(sourceNode.getId(), connectedNode.getId(), sourceNode.getPackets(), sourceNode.getDistance(connectedNode)) ;
      adjMatrix[connectedNode.getId()-1][sourceNode.getId()-1] = new Edge(connectedNode.getId(), sourceNode.getId(), connectedNode.getPackets(), connectedNode.getDistance(sourceNode));
    }
  

    //Breadth First Search Algorithm 
    public List<List<Node>> bfs(){ 
      List<List<Node>> components = new ArrayList<>(); //List containing individual lists of nodes that are connected 
      boolean[] visited = new boolean[numVertices]; //Array keeping track of visited nodes 
      
     
      for(int startNode = 0; startNode < numVertices; startNode++){ 
        if(!visited[startNode]){  
          List<Node> component = new ArrayList<>(); //List containing connect nodes
          Queue<Integer> queue = new LinkedList<>();   //Queue of nodes 

          queue.offer(startNode); 
          visited[startNode] = true; 

          while(!queue.isEmpty()){  
            int currentNode = queue.poll(); 
            component.add(NodeRegistry.getNodeById(currentNode +1)); //Converting from adj ID to node ID  

            //Search all other nodes besides currentNode to see if there is an edge in the adjMatrix, if so, add to queue 
            for(int neighbor =0; neighbor < numVertices; neighbor++){ 
              if(adjMatrix[currentNode][neighbor] != null && !visited[neighbor]){
                  queue.offer(neighbor); 
                  visited[neighbor] = true; 
              }
            }
          }
          components.add(component);
        }
      }

      return components; 
    } 


    public List<List<Node>> dfs(){ 
      List<List<Node>> components = new ArrayList<>(); 
      boolean[] visited = new boolean[numVertices];

      for(int startNode = 0; startNode < numVertices; startNode++){
        if(!visited[startNode]){
          List<Node> component = new ArrayList<>(); 
          Stack<Integer> stack = new Stack<>(); 

          stack.push(startNode);
          visited[startNode] = true; 

          while(!stack.isEmpty()){
            int currentNode = stack.pop();
            component.add(NodeRegistry.getNodeById(currentNode +1));

            for(int neighbor = 0; neighbor < numVertices; neighbor++){
              if(adjMatrix[currentNode][neighbor] != null&& !visited[neighbor]){
                stack.push(neighbor);
                visited[neighbor] = true; 
              }
            }
          }
          components.add(component);
        }
      }
      return components;
    }
} 
 